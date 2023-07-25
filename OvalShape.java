import java.awt.*;
class OvalShape extends Shape {
	public OvalShape() {}
	public OvalShape(Color c, Color bc, PathType pt) {
		super(c, bc, pt);
	}
	public OvalShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x ,y ,w, h ,pw ,ph, c, bc, pt);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.width, this.height);
		g.setColor(this.borderColor);
		g.drawOval(this.x, this.y, this.width, this.height);
	}
	@Override
	public boolean contains(Point mousePt) {
		double dx, dy;
		Point EndPt = new Point(x + width, y + height);
		dx = (2 * mousePt.x - x - EndPt.x) / (double) width;
		dy = (2 * mousePt.y - y - EndPt.y) / (double) height;
		return dx * dx + dy * dy < 1.0;
	}
}
