package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {

  private int radius;
  private Point loc;

  public SCircle(Point loc, int radius) {
    super();
    this.radius = radius;
    this.loc = loc;
  }

  public int getRadius() { return radius; }

  public void setRadius(int radius) { this.radius = radius; }

  @Override
  public Point getLoc() {
    return this.loc;
  }

  @Override
  public void setLoc(Point point) {
    this.loc = point;
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(this.loc.x - this.radius, this.loc.y - this.radius,
                         2 * radius, 2 * radius);
  }

  @Override
  public void accept(ShapeVisitor visitor) {
    visitor.visitCircle(this);
  }
}
