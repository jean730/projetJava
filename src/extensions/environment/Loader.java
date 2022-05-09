package extensions.environment;

import extensions.environment.entities.*;
import extensions.environment.ui.Animation;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
  private Scanner scanner;
  private TileMap tileMap;

  public Loader(String path) {
    try {
      this.scanner = new Scanner(new File(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String tileSet = this.scanner.next();
    int lines = this.scanner.nextInt();
    int rows = this.scanner.nextInt();
    int[][] map = new int[lines][rows];
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < rows; j++) {
        map[i][j] = this.scanner.nextInt();
      }
    }
    tileMap = new TileMap(tileSet, map);
  }

  public TileMap getTileMap() { return tileMap; }

  public void closeScanner() { this.scanner.close(); }

  public ArrayList<Entity> createEntityList(GameModel model) {
    ArrayList<Entity> entities = new ArrayList<>();
    while (this.scanner.hasNext()) {
      switch (this.scanner.next()) {
      case "Tree": {
        entities.add(new StaticEntity(
            new Point2D.Double(this.scanner.nextInt(), this.scanner.nextInt()),
            "assets/GrassLand/Details/GrassLand_Tree.png", "Tree"));
        break;
      }
      case "Cloud": {
        entities.add(new Cloud(
            model,
            new Point2D.Double(this.scanner.nextInt(), this.scanner.nextInt()),
            -20, "assets/GrassLand/Background/GrassLand_Cloud_1.png"));
        break;
      }
      case "Player": {
        entities.add(new Player(
            new Point2D.Double(this.scanner.nextInt(), this.scanner.nextInt()),
            model));
        break;
      }
      case "Fire": {
        StaticEntity fire = new StaticEntity(
            new Point2D.Double(this.scanner.nextInt(), this.scanner.nextInt()),
            "assets/Details/fire.png", "Fire");
        entities.add(fire);
        fire.getSprite().registerAnimation(
            "default", new Animation(32, 48, 256, 48, 0, 8, 80));
        fire.getSprite().setAnimation("default");
        break;
      }
      case "Pixie": {
        entities.add(new Pixie(model));
        scanner.nextInt();
        scanner.nextInt();
        break;
      }
      case "Enemy": {
        entities.add(new Enemy(
            new Point2D.Double(this.scanner.nextInt(), this.scanner.nextInt()),
            model));
      }
      }
    }
    return entities;
  }
}
