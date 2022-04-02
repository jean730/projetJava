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
		for(float vx = 0; vx < velocity.x; vx = vx+1) //vitesse en x positive
		{
			if(!onGround(tileMap, this.getLoc().x+vx+1, 0))
				this.translate(1, 0);
			else
				velocity.setLocation(0, velocity.y);
		}
		for(float vx = 0; vx > velocity.x; vx = vx-1) //vitesse en x négative
		{
			if(!onGround(tileMap, this.getLoc().x-vx-1, 0))
				this.translate(-1, 0);
			else
				velocity.setLocation(0, velocity.y);
		}
		for(float vy = 0; vy < velocity.y; vy = vy+1) //vitesse en yx positive
		{
			if(!onGround(tileMap, 0, this.getLoc().y+vy+1))
				this.translate(0, 1);
			else
				velocity.setLocation(velocity.x, 0);
		}
		for(float vy = 0; vy > velocity.y; vy = vy-1) //vitesse en y négative
		{
			if(!onGround(tileMap, 0, this.getLoc().y-vy-1))
				this.translate(0, -1);
			else
				velocity.setLocation(velocity.x, 0);
		}
	}

	public Boolean onGround(TileMap tileMap, float x, float y)
	{
		return tileMap.getTextureMap()[(int) (y/tileMap.TILEWIDTH)+1][(int) (x/tileMap.TILEWIDTH)+1] >= 0;
	}
}