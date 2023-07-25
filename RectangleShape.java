/*
 *	===============================================================================
 *	RectangleShape.java : This class defines a shape that is a rectangle for the user to create in the program.
 *  YOUR UPI: Jhaz480
 *	=============================================================================== */
import java.awt.*;
class RectangleShape extends Shape {
    public RectangleShape() {}
	public RectangleShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x ,y ,w, h ,pw ,ph, c, bc, pt);
	}
	public RectangleShape(Color c, Color bc, PathType pt) {
		super(c, bc, pt);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(this.borderColor);
		g.drawRect(this.x, this.y, this.width, this.height);
	}
	@Override
	public boolean contains(Point mousePt) {
		return (x <= mousePt.x && mousePt.x <= (x + width)	&&	y <= mousePt.y && mousePt.y <= (y + height));
	}
}