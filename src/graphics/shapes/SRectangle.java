package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

  private Rectangle rect;

  public SRectangle(Point point, int width, int height) {
    super();
    this.rect = new Rectangle(point.x, point.y, width, height);
  }

  public Rectangle getRect() { return rect; }

  @Override
  public Point getLoc() {
    return rect.getLocation();
  }

  @Override
  public void setLoc(Point point) {
    rect.setLocation(point);
  }

  @Override
  public Rectangle getBounds() {
    return this.rect;
  }

  @Override
  public void accept(ShapeVisitor visitor) {
    visitor.visitRectangle(this);
  }
}
