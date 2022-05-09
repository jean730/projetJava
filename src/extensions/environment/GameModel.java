package extensions.environment;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import extensions.GameMain;
import extensions.environment.audio.Audio;
import extensions.environment.entities.Cloud;
import extensions.environment.entities.Enemy;
import extensions.environment.entities.Entity;
import extensions.environment.entities.Pixie;
import extensions.environment.entities.Player;
import extensions.environment.entities.StaticEntity;
import extensions.environment.ui.Animation;
import graphics.shapes.Shape;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.ShapeVisitor;

import javax.imageio.IIOException;

public class GameModel extends Shape {

	private long time = System.nanoTime();
	private double dt;
	private TileMap tileMap;
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	private Pixie pixie;
	private Audio audio = new Audio();
	private boolean isFinished = false;
	private GameMain gameMain;
        private SText scoreShape;

	public GameModel(TileMap tileMap, Point2D.Double playerPos, GameMain gameMain) {
		this.tileMap = tileMap;
		Player p = new Player(playerPos, this);
		StaticEntity fire = new StaticEntity(new Point2D.Double(3900, 256), "assets/Details/fire.png", "Fire"); // Entité
																												// de
																												// test
		fire.getSprite().registerAnimation("default", new Animation(32, 48, 256, 48, 0, 8, 80));
		fire.getSprite().setAnimation("default");
		this.addEntity(new Cloud(this, new Point2D.Double(-200, 50), -20,
				"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
		this.addEntity(new Cloud(this, new Point2D.Double(-100, 80), -30,
				"assets/GrassLand/Background/GrassLand_Cloud_2.png"));
		this.addEntity(
				new Cloud(this, new Point2D.Double(0, 20), -20, "assets/GrassLand/Background/GrassLand_Cloud_3.png"));
		this.addEntity(new Cloud(this, new Point2D.Double(100, 100), -40,
				"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
		this.addEntity(
				new Cloud(this, new Point2D.Double(250, 30), -10, "assets/GrassLand/Background/GrassLand_Cloud_2.png"));
		this.addEntity(
				new Cloud(this, new Point2D.Double(400, 60), -50, "assets/GrassLand/Background/GrassLand_Cloud_3.png"));
		this.addEntity(p);
		this.addEntity(fire);
		Pixie pixie = new Pixie(this);
		this.addEntity(pixie);
		this.setPixie(pixie);
		this.addPlayer(p);
                this.scoreShape = new SText(new Point(15,510),"test");
                this.scoreShape.addAttributes(new ColorAttributes(false,false,Color.GRAY,Color.YELLOW));
                FontAttributes fontAttributes = new FontAttributes();
                fontAttributes.fontColor = Color.YELLOW;
                this.scoreShape.addAttributes(fontAttributes);

		Enemy q = new Enemy(new Point2D.Double(3900, 256), this);
		this.addEntity(q);
		this.gameMain = gameMain;
	}

	public GameModel(String path) {
		Loader loader = new Loader(path);
		this.tileMap = loader.getTileMap();
		this.entities = loader.createEntityList(this);
		this.extractPlayers();
		this.extractPixies();
		loader.closeScanner();
	}

	public void updateTime() {
		this.dt = ((double) (System.nanoTime() - this.time)) / 1000000000;
		this.time = System.nanoTime();
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setPixie(Pixie pixie) {
		this.pixie = pixie;
	}

	public Pixie getPixie() {
		return pixie;
	}

	public SText getScoreShape() {
		return scoreShape;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void gameLoop() {
		while (!this.isFinished) {
			this.applyPhysics();
		}
	}

	public void finish() {
		isFinished = true;
	}

	public void applyPhysics() {
		this.updateTime();
		entities.forEach((entity) -> entity.applyPhysics(tileMap, dt));
		players.forEach((player) -> player.applyColisions());
		for (int i = 0; i < entities.size(); i++) {
			if (this.entities.get(i).isDead()) {
				this.entities.remove(i);
			}
		}
	}

	@Override
	public Point getLoc() {
		return null;
	}

	@Override
	public void setLoc(Point point) {
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		((EnvironmentVisitor) visitor).visitGameModel(this);
	}

	public double getDt() {
		return dt;
	}

	public void save(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(this.tileMap.getTileSetPath() + " ");
			int[][] tab = this.tileMap.getTextureMap();
			writer.write(tab.length + " " + tab[0].length + "\n");
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[0].length; j++) {
					writer.write(tab[i][j] + " ");
				}
				writer.write("\n");
			}
			for (Entity e : this.entities) {
				writer.write(e.getType() + " " + e.getLoc().x + " " + e.getLoc().y + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void extractPlayers() {
		for (Entity entity : this.entities) {
			if (entity.getType() == "Player")
				this.addPlayer((Player) entity);
		}
	}

	public void extractPixies() {
		for (Entity entity : this.entities) {
			if (entity.getType() == "Pixie")
				this.setPixie((Pixie) entity);
		}
	}

	public GameMain getGameMain() {
		return gameMain;
	}

	public void addTiles() {
		int[][] newTextureMap = mergeTextureMaps(tileMap.getTextureMap(), new Generator("assets/GrassLand/Terrain/Grassland_Terrain_Tileset.png", tileMap.getTextureMap()[0].length, tileMap.getTextureMap().length).getTileMap().getTextureMap());
		if (newTextureMap!=null) {
			tileMap.setTextureMap(newTextureMap);
		}
	}

	int[][] mergeTextureMaps(int A[][], int B[][]) {

		try {
			int [][]C = new int[A.length][A[0].length + B[0].length];
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[0].length; j++) {
					C[i][j] = A[i][j];
				}
			}
			for (int i = 0; i < B.length; i++) {
				for (int j = 0; j < B[0].length; j++) {
					C[i][j + A[0].length] = B[i][j];
				}
			}
			return C;
		} catch (Exception e) {
			System.out.println("différentes tailles de matrices où matrice vides");
			return null;
		}
	}
}
