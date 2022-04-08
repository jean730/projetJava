package extensions.environment.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.TileMap;
import extensions.environment.GameModel;

public class Player extends Entity {
	
	private final double GRAVITY = -20;
	private final int JUMPSTRENGTH = 400;
	private GameModel gameModel;
	private Point2D.Double velocity = new Point2D.Double(-2,0);	
	
	public Player(Point2D.Double loc,GameModel gameModel) {
		super(loc,"assets/Sprites/ptitbonhome.png");
        this.gameModel = gameModel;
	}
	
	@Override
	public void applyPhysics(TileMap tileMap, double dt) {
		Point2D.Double doubleLoc = this.getDoubleLoc();
		if (!onGround(tileMap, doubleLoc))
			velocity.setLocation(velocity.x, velocity.y+GRAVITY*dt);
		Point2D.Double nextLoc = nextLoc(dt);
		System.out.println("------");
		System.out.println(onWallRight(tileMap,nextLoc));
		System.out.println(onWallLeft(tileMap,nextLoc));
		System.out.println(onGround(tileMap,nextLoc));
		System.out.println(onRoof(tileMap,nextLoc));
		if (velocity.x > 0) {
			if (!onWallRight(tileMap,nextLoc)) {
				this.setDoubleLoc(nextLoc);
			}
			else {
				this.velocity.x = 0;
				this.setDoubleLoc(new Point2D.Double(Math.ceil(doubleLoc.x),doubleLoc.y));
				doubleLoc = this.getDoubleLoc();
				while (!onWallRight(tileMap,doubleLoc))
					this.DoubleTranslate(1.0, 0.0);
			}
		}
		else if (velocity.x < 0) {
			if (!onWallLeft(tileMap,nextLoc)) {
				this.setDoubleLoc(nextLoc);
			}
			else {
				this.velocity.x = 0;
				this.setDoubleLoc(new Point2D.Double(Math.floor(doubleLoc.x),doubleLoc.y));
				doubleLoc = this.getDoubleLoc();
				while (!onWallLeft(tileMap,doubleLoc))
					this.DoubleTranslate(-1.0, 0.0);
			}
		}
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
		else if (velocity.y < 0) {
			if (!onRoof(tileMap,nextLoc)) {
				this.setDoubleLoc(nextLoc);
			}
			else {
				this.velocity.y = 0;
				this.setDoubleLoc(new Point2D.Double(doubleLoc.x,Math.floor(doubleLoc.y)));
				doubleLoc = this.getDoubleLoc();
				while (!onRoof(tileMap,doubleLoc))
					this.DoubleTranslate(0.0, -1.0);
			}
		}
	}
	
	public Point2D.Double nextLoc(double dt) {
		Point2D.Double doubleLoc = getDoubleLoc();
		return new Point2D.Double(doubleLoc.x + this.velocity.x * dt, doubleLoc.y + this.velocity.y * dt);
	}

	public Boolean onGround(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			return tileMap.getTextureMap()[(int) ((doubleLoc.y+this.getSprite().getHeight())/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0 && 
					tileMap.getTextureMap()[(int) ((doubleLoc.y+this.getSprite().getHeight())/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+1)/tileMap.TILEWIDTH)] >= 0 &&
					tileMap.getTextureMap()[(int) ((doubleLoc.y+this.getSprite().getHeight())/tileMap.TILEWIDTH)][(int) ((doubleLoc.x-1)/tileMap.TILEWIDTH)] >= 0;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}
	
	public Boolean onRoof(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			return tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0 && 
					tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+1)/tileMap.TILEWIDTH)] >= 0 &&
					tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) ((doubleLoc.x-1)/tileMap.TILEWIDTH)] >= 0;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}
	
	public Boolean onWallLeft(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			return tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0 && 
					tileMap.getTextureMap()[(int) ((doubleLoc.y+1)/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0 &&
					tileMap.getTextureMap()[(int) ((doubleLoc.y-1)/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}
	
	public Boolean onWallRight(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			return tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+this.getSprite().getWidth())/tileMap.TILEWIDTH)] >= 0 && 
					tileMap.getTextureMap()[(int) ((doubleLoc.y+1)/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+this.getSprite().getWidth())/tileMap.TILEWIDTH)] >= 0 &&
					tileMap.getTextureMap()[(int) ((doubleLoc.y-1)/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+this.getSprite().getWidth())/tileMap.TILEWIDTH)] >= 0;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}

	public void die() {
	
	}
	
	private void jump() {
		this.velocity.y = -JUMPSTRENGTH;
		this.gameModel.getAudio().play("jump");
	}

	public void conditionalJumpFunctionToJumpOnlyOnGround(TileMap tileMap) {
		if (onGround(tileMap,this.getDoubleLoc())) this.jump();
	}
}
