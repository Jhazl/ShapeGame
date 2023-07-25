import java.awt.*;
import java.util.Arrays;
class OctagonShape extends Shape {
	public OctagonShape() {}
	public OctagonShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x ,y ,w, h ,pw ,ph, c, bc, pt);
	}
	public OctagonShape(Color c, Color bc, PathType pt) {
		super(c, bc, pt);
	}
	@Override
	public void draw(Graphics g) {
		int[] xCoord = new int[]{x+width/3, x+2*width/3, x+width, x+width, x+2*width/3, x+width/3, x, x};
		int[] yCoord = new int[]{y, y, y+height/3, y+2*height/3, y+height, y+height, y+2*height/3, y+height/3};
		g.setColor(this.color);
		g.fillPolygon(xCoord, yCoord, 8);
		g.setColor(this.borderColor);
		g.drawPolygon(xCoord, yCoord, 8);
	}
	@Override
	public boolean contains(Point mousePt) {
		int[] xCoord = new int[]{x+width/3, x+2*width/3, x+width, x+width, x+2*width/3, x+width/3, x, x};
		int[] yCoord = new int[]{y, y, y+height/3, y+2*height/3, y+height, y+height, y+2*height/3, y+height/3};
		Polygon polygon = new Polygon(xCoord, yCoord, 5);
		return polygon.contains(mousePt);
	}
}