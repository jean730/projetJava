package extensions.environment;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.entities.Cloud;
import extensions.environment.entities.Enemy;
import extensions.environment.entities.Entity;
import extensions.environment.entities.Player;
import extensions.environment.entities.StaticEntity;
import extensions.environment.ui.Animation;
import extensions.environment.audio.Audio;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;

public class GameModel extends Shape {
	
	private long time = System.nanoTime();
	private double dt;
	private TileMap tileMap;
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	private Audio audio = new Audio();
	private boolean isFinished = true;
	
	public GameModel(TileMap tileMap) {
		this.tileMap = tileMap;
		Player p = new Player(new Point2D.Double(50,100), this);
		StaticEntity fire = new StaticEntity(new Point2D.Double(100,192),"assets/Details/fire.png"); // Entité de test
		fire.getSprite().registerAnimation("default",new Animation(32,48,256,48,0,8,80));
		fire.getSprite().setAnimation("default");
		this.addEntity(new Cloud(new Point2D.Double(-200,50),-20,"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
		this.addEntity(new Cloud(new Point2D.Double(-100,80),-30,"assets/GrassLand/Background/GrassLand_Cloud_2.png"));
		this.addEntity(new Cloud(new Point2D.Double(0,20),-20,"assets/GrassLand/Background/GrassLand_Cloud_3.png"));
		this.addEntity(new Cloud(new Point2D.Double(100,100),-40,"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
		this.addEntity(new Cloud(new Point2D.Double(250,30),-10,"assets/GrassLand/Background/GrassLand_Cloud_2.png"));
		this.addEntity(new Cloud(new Point2D.Double(400,60),-50,"assets/GrassLand/Background/GrassLand_Cloud_3.png"));
		this.addEntity(p);
		this.addEntity(fire);
		this.addPlayer(p);
		Enemy q = new Enemy(new Point2D.Double(100,150),this);
		this.addEntity(q);
	}

	public void updateTime() {
		this.dt = ((double)(System.nanoTime() - this.time))/1000000000;
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
		while (this.isFinished) {
			this.applyPhysics();
		}
	}
	
	public void applyPhysics() {
		this.updateTime();
		entities.forEach((entity) -> entity.applyPhysics(tileMap , dt));
		players.forEach((player)->player.applyColisions());
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
}
