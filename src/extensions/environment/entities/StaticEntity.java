package extensions.environment.entities;

import extensions.environment.TileMap;
import extensions.environment.ui.Animation;
import extensions.environment.ui.Sprite;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class StaticEntity extends Entity {
  public StaticEntity(Point2D.Double loc, String fileName, String type) {
    super(loc, type);
    this.sprite = new Sprite(fileName);
  }

  @Override
  public void applyPhysics(TileMap tileMap, double dt) {}
  public void die() {}
}
