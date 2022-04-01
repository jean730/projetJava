package extensions.environment.entities;

import java.awt.Point;
import java.awt.geom.Point2D;

import extensions.environment.TileMap;

public class Player extends Entity {
	
	private final double GRAVITY = 1; 
	
	private Boolean onRoof;
	private Point2D.Float velocity;
	
	public Player(Point loc) {
		super(loc);
	}

	@Override
	public void applyPhysics(TileMap tileMap) {
		System.out.println(this.getLoc().x+" "+this.getLoc().y);
		if(onGround(tileMap))
			velocity.setLocation(velocity.x, velocity.y+GRAVITY);
		this.translate((int) velocity.x, (int) velocity.y);
	}

	public Boolean onGround(TileMap tileMap)
	{
		int x = this.getLoc().x;
		int y = this.getLoc().y;
		return tileMap.getTextureMap()[x/tileMap.TILEWIDTH][y/tileMap.TILEWIDTH] > 0;
	}
}
