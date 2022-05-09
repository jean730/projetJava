package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SImage extends Shape {

  private Rectangle rect;
  private BufferedImage img;

  public SImage(String fileName, Point point) {
    super();
    try {
      img = ImageIO.read(new File(fileName));
    } catch (IOException e) {
      System.err.println("Could not Initialize SImage: " + e);
    }
    this.rect =
        new Rectangle(point.x, point.y, img.getWidth(), img.getHeight());
  }

  public Rectangle getRect() { return rect; }

  public BufferedImage getImage() { return img; }

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
    visitor.visitImage(this);
  }
}
