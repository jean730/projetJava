package graphics.shapes;
import graphics.shapes.attributes.FontAttributes;
import java.awt.Point;
import java.awt.Rectangle;

public class SText extends Shape {

  private String text;
  private Point loc;

  public SText(Point point, String string) {
    this.loc = point;
    this.text = string;
  }

  public String getText() { return text; }

  public void setText(String text) { this.text = text; }

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
    Rectangle rect =
        ((FontAttributes)this.getAttributes().get("font")).getBounds(this.text);
    rect.translate(loc.x, loc.y);
    return rect;
  }

  @Override
  public void accept(ShapeVisitor visitor) {
    visitor.visitText(this);
  }
}
