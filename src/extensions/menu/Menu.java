package extensions.menu;

import extensions.GameMain;
import graphics.shapes.SCollection;

public abstract class Menu extends SCollection {

  private GameMain gameMain;

  public Menu(GameMain gameMain) { this.gameMain = gameMain; }

  public GameMain getGameMain() { return gameMain; }
}
