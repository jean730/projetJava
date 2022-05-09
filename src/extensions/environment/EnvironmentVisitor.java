package extensions.environment;

import extensions.environment.entities.Entity;
import graphics.shapes.ShapeVisitor;

public interface EnvironmentVisitor extends ShapeVisitor {

  public abstract void visitTileMap(TileMap tileMap);

  public abstract void visitEntity(Entity entity);

  public abstract void visitGameModel(GameModel gameModel);
}
