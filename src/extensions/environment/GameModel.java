package extensions.environment;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import extensions.environment.entities.Entity;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;

public class GameModel extends Shape {

	private TileMap tileMap = new TileMap();
	private ArrayList<Entity> entities = new ArrayList<>();
	
	public GameModel(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
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
		((EnvironmentVisitor) visitor).visitGameModel(this);;
	}
}
