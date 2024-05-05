package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;

public class TextWrap {
    private String descrizione;
    private int fontSize;
    private Rectangle recBounds;
    private Color tint;

    public TextWrap(String descrizione, int fontSize, Rectangle recBounds, Color tint) {
        this.descrizione = descrizione;
        this.fontSize = fontSize;
        this.recBounds = recBounds;
        this.tint = tint;
    }

	public void draw() {
        Font font = rText.GetFontDefault();
        int spacing = 2;

        int length = rText.TextLength(descrizione);  // Total length in bytes of the text, scanned by codepoints in loop

		float textOffsetY = 0;          // Offset between lines (on line break '\n')
		float textOffsetX = 0.0f;       // Offset X to next character to draw

		float scaleFactor = fontSize / (float) font.baseSize;     // Character rectangle scaling factor

		boolean state = true;

		int startLine = -1;         // Index where to begin drawing (where a line begins)
		int endLine = -1;           // Index where to stop drawing (where a line ends)
		int lastk = -1;             // Holds last value of the character position

		for (int i = 0, k = 0; i < length; i++, k++) {
			// Get next codepoint from byte string and glyph index in font
			int codepointByteCount = 0;
			int codepoint = rText.GetCodepoint(descrizione.substring(i).toCharArray(), codepointByteCount);
			codepointByteCount = rText.getCPBC();
			int index = rText.GetGlyphIndex(font, codepoint);

			// NOTE: Normally we exit the decoding sequence as soon as a bad byte is found (and return 0x3f)
			// but we need to draw all of the bad bytes using the '?' symbol moving one byte
			if (codepoint == 0x3f) codepointByteCount = 1;
			i += (codepointByteCount - 1);

			float glyphWidth = 0;
			if (codepoint != '\n') {
				glyphWidth = (font.glyphs[index].advanceX == 0) ? font.recs[index].width * scaleFactor : font.glyphs[index].advanceX * scaleFactor;

				if (i + 1 < length) glyphWidth = glyphWidth + spacing;
			}

			// NOTE: When wordWrap is ON we first measure how much of the text we can draw before going outside of the rec container
			// We store this info in startLine and endLine, then we change states, draw the text between those two variables
			// and change states again and again recursively until the end of the text (or until we get outside of the container).
			// When wordWrap is OFF we don't need the measure state so we go to the drawing state immediately
			// and begin drawing on the next line before we can get outside the container.
			if (state) {
				if (codepoint == ' ' || codepoint == '\t' || codepoint == '\n') endLine = i;

				if ((textOffsetX + glyphWidth) > recBounds.width) {
					endLine = (endLine < 1)? i : endLine;
					if (i == endLine) endLine -= codepointByteCount;
					if ((startLine + codepointByteCount) == endLine) endLine = (i - codepointByteCount);

					state = !state;
				} else if (i + 1 == length) {
					endLine = i;
					state = !state;
				} else if (codepoint == '\n') state = !state;

				if (!state) {
					textOffsetX = 0;
					i = startLine;
					glyphWidth = 0;

					// Save character position when we switch states
					int tmp = lastk;
					lastk = k - 1;
					k = tmp;
				}
			} else {
				if (codepoint != '\n') {
					// When text overflows rectangle height limit, just stop drawing
					if ((textOffsetY + font.baseSize * scaleFactor) > recBounds.height) break;

					// Draw current character glyph
					if ((codepoint != ' ') && (codepoint != '\t')) {
						Finestra.getRaylib().text.DrawTextCodepoint(font, codepoint, new Vector2(recBounds.x + textOffsetX, recBounds.y + textOffsetY), fontSize, tint);
					}
				}

				if (i == endLine) {
					textOffsetY += (font.baseSize + font.baseSize / 2) * scaleFactor;
					textOffsetX = 0;
					startLine = endLine;
					endLine = -1;
					glyphWidth = 0;
					k = lastk;

					state = !state;
				}
			}

			if ((textOffsetX != 0) || (codepoint != ' ')) textOffsetX += glyphWidth;  // avoid leading spaces
		}
    }

    public void setDescription(String descrizione) {
        this.descrizione = descrizione;
    }
}