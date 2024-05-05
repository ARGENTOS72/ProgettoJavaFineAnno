package view;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;

public class TextWrap {
	private Rectangle bounds;
    private String text;
    private Color color;
    private int fontSize;

    //Constructor ---------------------------
    public TextWrap(String text, int fontSize, int x, int y, int width, int height, Color color) {
        this.text = text;
        this.fontSize = fontSize;
        this.bounds = new Rectangle(x, y, width, height);
        this.color = color;
    }

    //draw ------------------------
	public void draw() {
        Font font = rText.GetFontDefault();
        int spacing = 2;

        int length = rText.TextLength(text);  // Total length in bytes of the text, scanned by codepoints in loop

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
			int codepoint = rText.GetCodepoint(text.substring(i).toCharArray(), codepointByteCount);
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

				if ((textOffsetX + glyphWidth) > bounds.width) {
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
					if ((textOffsetY + font.baseSize * scaleFactor) > bounds.height) break;

					// Draw current character glyph
					if ((codepoint != ' ') && (codepoint != '\t')) {
						Finestra.getRaylib().text.DrawTextCodepoint(font, codepoint, new Vector2(bounds.x + textOffsetX, bounds.y + textOffsetY), fontSize, color);
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

	//getters & setters -------------------------
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getLocation() {
		return new Vector2(bounds.x, bounds.y);
	}
	
	public Vector2 getSize() {
		return new Vector2(bounds.width, bounds.height);
	}
	
	public int getX() {
		return (int) bounds.x;
	}
	
	public int getY() {
		return (int) bounds.y;
	}
	
	public int getWidth() {
		return (int) bounds.width;
	}
	
	public int getHeight() {
		return (int) bounds.height;
	}
	
	public String getText() {
		return text;
	}

	public int getFontSize() {
		return fontSize;
	}

	public Color getColor() {
		return color;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		bounds.x = x;
		bounds.y = y;
		bounds.width = width;
		bounds.height = height;
	}
	
	public void setLocation(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}
	
	public void setSize(int width, int height) {
		bounds.width = width;
		bounds.height = height;
	}
	
	public void setX(int x) {
		bounds.x = x;
	}
	
	public void setY(int y) {
		bounds.y = y;
	}
	
	public void setWidth(int width) {
		bounds.width = width;
	}
	
	public void setHeight(int height) {
		bounds.height = height;
	}

	public void setTint(Color color) {
		this.color = color;
	}
}
