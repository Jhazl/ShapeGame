/* ==============================================
 *	Shape.java : The superclass of all shapes.
 *	A shape defines various properties, including selected, colour, width and height.
 *	YOUR UPI: Jhaz480
 *	===============================================================================
 */
import java.awt.*;
abstract class Shape {
    public static final PathType DEFAULT_PATHTYPE = PathType.BOUNCING;
    public static final ShapeType DEFAULT_SHAPETYPE = ShapeType.RECTANGLE;
    public static final int DEFAULT_X = 0, DEFAULT_Y = 0, DEFAULT_WIDTH=60, DEFAULT_HEIGHT=60, DEFAULT_PANEL_WIDTH=600, DEFAULT_PANEL_HEIGHT=800;
    public static final Color DEFAULT_COLOR=Color.white, DEFAULT_BORDER_COLOR=Color.orange;
    public int x, y, width=DEFAULT_WIDTH, height=DEFAULT_HEIGHT, panelWidth=DEFAULT_PANEL_WIDTH, panelHeight=DEFAULT_PANEL_HEIGHT; // the bouncing area
    protected Color color = DEFAULT_COLOR, borderColor =DEFAULT_BORDER_COLOR ;
    protected boolean selected = false;    // draw handles if selected
    protected MovingPath path = new BouncingPath(1, 2);
    public static final String DEFAULT_LABEL = "0";
    protected String label = DEFAULT_LABEL;
    public static int count = 0;
    public Shape() {
        label = Integer.toString(count);
        count++;
    }
    public Shape(Color c, Color bc, PathType pt) {
    	this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT, c, bc, pt);
    	label = Integer.toString(count);
        count++;
    }
    public Shape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
        this.x = x;
        this.y = y;
        panelWidth = pw;
        panelHeight = ph;
        width = w;
        height = h;
        color = c;
        borderColor = bc;
        label = Integer.toString(count);
        count++;
    	switch (pt) {
    		case BOUNCING : {
    			path = new BouncingPath(1, 2);
    			break;
    		} case FLOATING : {
    	    path = new FloatingPath(2);
    	    break;
    		}
    	}
    }
    public String getLabel(){
        return label;
    }

    public void setLabel(String t){
        label = t;
    }

    public void drawString(Graphics g){
        g.setColor(new Color(000));
        g.drawString(label, x, y + height);
    }

     public String toString() {
    		return String.format("%s:[(%d,%d),%dx%d(%s),%s]", getClass().getName(), x,y,width,height,path.getClass().getName(), label);
    }
     
    public void move() { path.move();}
    public abstract boolean contains(Point p);
    public abstract void draw(Graphics g);
	// you don't need to make any changes after this line ______________
    public int getX() { return this.x; }
    public int getY() { return this.y;}
	public int getWidth() { return width; }
	public void setWidth(int w) { if (w < DEFAULT_PANEL_WIDTH && w > 0) width = w; }
	public int getHeight() {return height; }
	public void setHeight(int h) { if (h < DEFAULT_PANEL_HEIGHT && h > 0) height = h; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean s) { selected = s; }
	public Color getColor() { return color; }
    public void setColor(Color fc) { color = fc; }
	public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color bc) { borderColor = bc; }
    public void resetPanelSize(int w, int h) {
		panelWidth = w;
		panelHeight = h;
	}
    public void drawHandles(Graphics g) {
		if (isSelected()) {
			g.setColor(Color.black);
			g.fillRect(x -2, y-2, 4, 4);
			g.fillRect(x + width -2, y + height -2, 4, 4);
			g.fillRect(x -2, y + height -2, 4, 4);
			g.fillRect(x + width -2, y-2, 4, 4);
		}
    }
    /* Inner class ===================================================================== Inner class
     *    MovingPath : The superclass of all paths. It is an inner class.
     *    A path can change the current position of the shape.
     *    =============================================================================== */
    abstract class MovingPath {
        protected int deltaX, deltaY; // moving distance
        public MovingPath(int dx, int dy) { deltaX=dx; deltaY=dy; }
        public MovingPath() { }
        public abstract void move();
        public String toString() { return getClass().getName(); };
    }
    class BouncingPath extends MovingPath {
        public BouncingPath(int dx, int dy) {
            super(dx, dy);
         }
        public void move() {
             x = x + deltaX;
             y = y + deltaY;
             if ((x < 0) && (deltaX < 0)) {
                 deltaX = -deltaX;
                 x = 0;
             }
             else if ((x + width > panelWidth) && (deltaX > 0)) {
                 deltaX = -deltaX;
                 x = panelWidth - width;
             }
             if ((y< 0) && (deltaY < 0)) {
                 deltaY = -deltaY;
                 y = 0;
             }
             else if((y + height > panelHeight) && (deltaY > 0)) {
                 deltaY = -deltaY;
                 y = panelHeight - height;
             }
        }
    }
    class FloatingPath extends MovingPath {
        public FloatingPath(int dx) { super(dx, 2); }
		public void move() {
			 x = x + deltaX;
			 y += deltaY;
			 if (x + width > panelWidth) {
				 x = 0;
			 }
			 deltaY = -deltaY;
		}
    }
}