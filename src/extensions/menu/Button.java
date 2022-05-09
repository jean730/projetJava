package extensions.menu;

import graphics.shapes.SImage;
import java.awt.Point;

public class Button extends SImage {

  private String id;

  public Button(Point point, String fileName, String id) {
    super(fileName, point);
    this.id = id;
  }

  public String getId() { return id; }
}
