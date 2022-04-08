package extensions.environment;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import extensions.environment.entities.Entity;
import extensions.environment.entities.Player;
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
	
	public GameModel(TileMap tileMap) {
		this.tileMap = tileMap;
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
