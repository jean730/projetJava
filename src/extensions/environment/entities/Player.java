package extensions.environment.entities;

import java.awt.Point;
import java.awt.geom.Point2D;

import extensions.environment.TileMap;

public class Player extends Entity {
	
	private final double GRAVITY = 1; 
	
	private Boolean onRoof;
	private Point2D.Float velocity = new Point2D.Float(0,0);
	
	public Player(Point loc) {
		super(loc);
	}

	@Override
	public void applyPhysics(TileMap tileMap) {
		velocity.setLocation(velocity.x, velocity.y+GRAVITY);
		if(!onGround(tileMap))
			this.translate(velocity.x, velocity.y);
		else
			velocity.setLocation(0, 0);
	}

	public Boolean onGround(TileMap tileMap)
	{
		float x = this.getLoc().x+velocity.x;
		float y = this.getLoc().y+velocity.y;
		return tileMap.getTextureMap()[(int) (y/tileMap.TILEWIDTH)+1][(int) (x/tileMap.TILEWIDTH)+1] >= 0;
	}
}
