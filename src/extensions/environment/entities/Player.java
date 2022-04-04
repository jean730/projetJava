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
		int x = this.getLoc().x;
		int y = this.getLoc().y;
		velocity.setLocation(velocity.x, velocity.y+GRAVITY);
		for(float dx = 0; dx < velocity.x; dx = dx+1) //vitesse en x positive
		{
			if(!onGround(tileMap, x+dx+tileMap.TILEWIDTH, y))
				this.translate(1, 0);
			else
				velocity.setLocation(0, velocity.y);
		}
		for(float dx = 0; dx > velocity.x; dx = dx-1) //vitesse en x négative
		{
			if(!onGround(tileMap, x+dx-tileMap.TILEWIDTH, y))
				this.translate(-1, 0);
			else
				velocity.setLocation(0, velocity.y);
		}
		for(float dy = 0; dy < velocity.y; dy = dy+1) //vitesse en y positive
		{
			if(!onGround(tileMap, x, y+dy+tileMap.TILEWIDTH))
				this.translate(0, 1);
			else
				velocity.setLocation(velocity.x, 0);
		}
		for(float dy = 0; dy > velocity.y; dy = dy-1) //vitesse en y négative
		{
			if(!onGround(tileMap, x, y+dy-tileMap.TILEWIDTH))
				this.translate(0, -1);
			else
				velocity.setLocation(velocity.x, GRAVITY);
		}
	}

	public Boolean onGround(TileMap tileMap, float x, float y)
	{
		return tileMap.getTextureMap()[(int) (y/tileMap.TILEWIDTH)][(int) (x/tileMap.TILEWIDTH)] >= 0;
	}
}