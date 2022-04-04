package extensions.environment.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.TileMap;

public class Player extends Entity {
	
	private final double GRAVITY = 0.01; 
	 
	private Boolean onRoof;
	private Point2D.Double velocity = new Point2D.Double(0,0);
	
	public Player(Point2D.Double loc) {
		super(loc);
	}

	@Override
	public void applyPhysics(TileMap tileMap, double dt) {
		Point2D.Double doubleLoc = this.getDoubleLoc();
		velocity.setLocation(velocity.x, velocity.y+GRAVITY);
		Point2D.Double nextLoc = nextLoc(dt);
		if (velocity.y > 0) {
			if (!onGround(tileMap,nextLoc)) {
				this.setDoubleLoc(nextLoc);
			}
			else {
				this.velocity.y = 0;
				this.setDoubleLoc(new Point2D.Double(doubleLoc.x,Math.ceil(doubleLoc.y)));
				doubleLoc = this.getDoubleLoc();
				while (!onGround(tileMap,doubleLoc))
					this.DoubleTranslate(0.0, 1.0);
			}
		}
<<<<<<< HEAD
	}
	
	public Point2D.Double nextLoc(double dt) {
		Point2D.Double doubleLoc = getDoubleLoc();
		return new Point2D.Double(doubleLoc.x + this.velocity.x * dt, doubleLoc.y + this.velocity.y * dt);
	}

	public Boolean onGround(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			return tileMap.getTextureMap()[(int) ((doubleLoc.y+this.getSprite().getHeight())/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0;
		} catch (Exception e) {
			this.die();
			return true;
=======
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
>>>>>>> 547907fc5891838333445a3aaeaaf59e6d20a68b
		}
	}

	public void die() {
	
	}
}
