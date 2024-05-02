package view;

public interface GraphicComponentAligner {
	public static final int NONE = -1, LEFT = 0, UP = 0, CENTER = 1, RIGHT = 2, DOWN = 2;//for align X & Y
	public static final int GROW = 10, DISTRIBUTE = 11, PADDING = 12;//alignType
	
	//mother method -----------------------------
	//align gcs hori
	public static void alignX(GraphicComponent[] gcArr, int align, int alignType, int offset, int dim, int padding) {
		if(align == LEFT) {
			if(alignType == PADDING) leftX(gcArr, offset, dim, padding);
			else leftX(gcArr, offset, dim);
			
		} else if(align == CENTER) {
			if(alignType == GROW) centerX(gcArr, offset, dim, true);
			else if(alignType == DISTRIBUTE) centerX(gcArr, offset, dim, false);
			else centerX(gcArr, offset, dim, padding);
			
		} else if(align == RIGHT) {
			if(alignType == PADDING) rightX(gcArr, offset, dim, padding);
			else rightX(gcArr, offset, dim);
			
		}
	}
	
	public static void alignY(GraphicComponent[] gcArr, int align, int alignType, int offset, int dim, int padding) {
		if(align == UP) {
			if(alignType == PADDING) upY(gcArr, offset, dim, padding);
			else upY(gcArr, offset, dim);
			
		} else if(align == CENTER) {
			if(alignType == GROW) centerY(gcArr, offset, dim, true);
			else if(alignType == DISTRIBUTE) centerY(gcArr, offset, dim, false);
			else centerY(gcArr, offset, dim, padding);
			
		} else if(align == DOWN) {
			if(alignType == PADDING) downY(gcArr, offset, dim, padding);
			else downY(gcArr, offset, dim);
		}
	}
	
	//LEFT --------------------------------------------------
	//grow left X
	public static void leftX(GraphicComponent[] gcArr, int offset, int width) {
		if(gcArr.length == 0) return;
		
		int sumW = 0;
		
		for(GraphicComponent gc : gcArr) sumW+= gc.getWidth();
		
		int totGapW = width - sumW;
		int gapW = totGapW / gcArr.length;
		
		gcArr[0].setX(offset);
		
		for(int i=1; i<gcArr.length; i++) {
			gcArr[i].setX(gcArr[i-1].getX()+gcArr[i-1].getWidth()+gapW);
		}
	}
	
	//padding left X
	public static void leftX(GraphicComponent[] gcArr, int offset, int width, int padding) {
		if(gcArr.length == 0) return;
		
		gcArr[0].setX(offset);
		
		for(int i=1; i<gcArr.length; i++) {
			gcArr[i].setX(gcArr[i-1].getX()+gcArr[i-1].getWidth()+(padding*2));
		}
	}
	
	//grow up Y
	public static void upY(GraphicComponent[] gcArr, int offset, int height) {
		if(gcArr.length == 0) return;
		
		int sumH = 0;
		
		for(GraphicComponent gc : gcArr) sumH+= gc.getHeight();
		
		int totGapH = height - sumH;
		int gapH = totGapH / gcArr.length;
		
		gcArr[0].setY(offset);
		
		for(int i=1; i<gcArr.length; i++) {
			gcArr[i].setY(gcArr[i-1].getY()+gcArr[i-1].getHeight()+gapH);
		}
	}
	
	//padding up Y
	public static void upY(GraphicComponent[] gcArr, int offset, int height, int padding) {
		if(gcArr.length == 0) return;
		
		gcArr[0].setY(offset);
		
		for(int i=1; i<gcArr.length; i++) {
			gcArr[i].setY(gcArr[i-1].getY()+gcArr[i-1].getHeight()+(padding*2));
		}
	}
	
	//CENTER ----------------------------------------------
	//single center X
	public static void centerX(GraphicComponent gc, int offset, int width) {
		gc.setX(offset + ((width / 2) - (gc.getWidth() / 2)));
	}
	
	//grow/distribute center X
	//center gcs using all of the provided space from x to x+width in the X-axis
	public static void centerX(GraphicComponent[] gcArr, int offset, int width, boolean grow) {
		if(gcArr.length == 0) return;
		
		if(gcArr.length == 1) centerX(gcArr[0], offset, width);
		else {
			int sumW = 0;
			
			for(GraphicComponent gc : gcArr) sumW+= gc.getWidth();
			
			int totGapW = width - sumW;
			
			int totSpaces = ((grow) ? -1 : 1);
			
			int rest = totGapW % (gcArr.length+totSpaces);
			int gapW = totGapW / (gcArr.length+totSpaces);
			
			gcArr[0].setX(offset + ((grow) ? 0 : gapW));// add 1?
			
			for(int i=1; i<gcArr.length; i++) {
				int restAdd = 0;
				if(rest > 0) {
					restAdd++;
					rest--;
				}
				gcArr[i].setX(gcArr[i-1].getX()+gcArr[i-1].getWidth()+gapW+restAdd);
			}
		}
	}
	
	//padding center x
	//center gcs between offset and offset + width with padding
	public static void centerX(GraphicComponent[] gcArr, int offset, int width, int padding) {
		if(gcArr.length == 0) return;
		
		if(gcArr.length == 1) centerX(gcArr[0], offset, width);
		else {
			int sumW = padding*(gcArr.length-1)*2;
			
			for(GraphicComponent gc : gcArr) sumW+= gc.getWidth();
			
			int startX = offset + ((width / 2) - (sumW / 2));
			
			gcArr[0].setX(startX);
			
			for(int i=1; i<gcArr.length; i++) {
				gcArr[i].setX(gcArr[i-1].getX()+gcArr[i-1].getWidth()+(padding*2));
			}
		}
	}
	
	//center gc between offset and offset + height
	public static void centerY(GraphicComponent gc, int offset, int height) {
		gc.setY(offset + ((height / 2) - (gc.getHeight() / 2)));
	}
	
	//grow/distribute center Y
	//center gcs using all of the provided space from y to y+width in the Y-axis
	public static void centerY(GraphicComponent[] gcArr, int offset, int height, boolean grow) {
		if(gcArr.length == 0) return;
		
		if(gcArr.length == 1) centerY(gcArr[0], offset, height);
		else {
			int sumH = 0;
			
			for(GraphicComponent gc : gcArr) sumH+= gc.getHeight();
			System.out.println(sumH);
			int totGapH = height - sumH;
			
			int totSpaces = ((grow) ? -1 : 1);
			
			int rest = totGapH % (gcArr.length+totSpaces);
			int gapH = totGapH / (gcArr.length+totSpaces);
			
			gcArr[0].setY(offset + ((grow) ? 0 : gapH));// add 1?
			
			for(int i=1; i<gcArr.length; i++) {
				int restAdd = 0;
				if(rest > 0) {
					restAdd++;
					rest--;
				}
				gcArr[i].setY(gcArr[i-1].getY()+gcArr[i-1].getHeight()+gapH+restAdd);
			}
		}
	}
	
	//center gcs between offset and offset + width with padding
	public static void centerY(GraphicComponent[] gcArr, int offset, int height, int padding) {
		if(gcArr.length == 0) return;
		
		if(gcArr.length == 1) centerY(gcArr[0], offset, height);
		else {
			int sumH = padding*(gcArr.length-1)*2;
			
			for(GraphicComponent gc : gcArr) sumH+= gc.getHeight();
			
			int startY = offset + ((height / 2) - (sumH / 2));
			
			gcArr[0].setY(startY);
			
			for(int i=1; i<gcArr.length; i++) {
				gcArr[i].setY(gcArr[i-1].getY()+gcArr[i-1].getHeight()+(padding*2));
			}
		}
	}
	
	//RIGHT ------------------------------------------------
	//grow right X
		public static void rightX(GraphicComponent[] gcArr, int offset, int width) {
			if(gcArr.length == 0) return;
			
			int sumW = 0;
			
			for(GraphicComponent gc : gcArr) sumW+= gc.getWidth();
			
			int totGapW = width - sumW;
			int gapW = totGapW / gcArr.length;
			
			gcArr[gcArr.length-1].setX(offset+width-gcArr[gcArr.length-1].getWidth());
			
			for(int i=gcArr.length-2; i>=0; i--) {
				gcArr[i].setX(gcArr[i+1].getX()-gcArr[i].getWidth()-gapW);
			}
		}
		
	//padding right X
	public static void rightX(GraphicComponent[] gcArr, int offset, int width, int padding) {
		if(gcArr.length == 0) return;
		
		gcArr[gcArr.length-1].setX(offset+width-gcArr[gcArr.length-1].getWidth());
		
		for(int i=gcArr.length-2; i>=0; i--) {
			gcArr[i].setX(gcArr[i+1].getX()-gcArr[i].getWidth()-(padding*2));
		}
	}
	
	//grow down Y
	public static void downY(GraphicComponent[] gcArr, int offset, int height) {
		if(gcArr.length == 0) return;
		
		int sumH = 0;
		
		for(GraphicComponent gc : gcArr) sumH+= gc.getHeight();
		
		int totGapH= height - sumH;
		int gapH = totGapH / gcArr.length;
		
		gcArr[gcArr.length-1].setY(offset+height-gcArr[gcArr.length-1].getHeight());
		
		for(int i=gcArr.length-2; i>=0; i--) {
			gcArr[i].setY(gcArr[i+1].getY()-gcArr[i].getHeight()-gapH);
		}
	}
		
	//padding down X
	public static void downY(GraphicComponent[] gcArr, int offset, int height, int padding) {
		if(gcArr.length == 0) return;
		
		gcArr[gcArr.length-1].setY(offset+height-gcArr[gcArr.length-1].getHeight());
		
		for(int i=gcArr.length-2; i>=0; i--) {
			gcArr[i].setY(gcArr[i+1].getY()-gcArr[i].getHeight()-(padding*2));
		}
	}
	
}
