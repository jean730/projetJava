package extensions.environment.entities;

import java.awt.*;
import java.awt.geom.Point2D;

import extensions.environment.TileMap;
import extensions.environment.GameModel;
import extensions.environment.ui.Sprite;
import extensions.environment.ui.Animation;

public class Player extends Entity {
	
	private static final double MAXFALLSPEED = 1000;

	private static final double MAXWALKSPEED = 500;
	private static final double SPRINTFACTOR = 0.4;

	private static final double GRAVITY = 1000;
	private static final double JUMPSTRENGTH = 400;

	private static final double WALKSTRENGTH = 200;
	private static final double FRICTIONFACTOR = 0.9999;
	private static final double FRICTIONMINSPEED = 10;

	private int walkingDirection = 0;
	private boolean isSprinting = false;
	private Point2D.Double velocity = new Point2D.Double(0,0);
	private GameModel gameModel;
	
	public Player(Point2D.Double loc,GameModel gameModel) {
            super(loc);
            this.sprite = new Sprite("assets/Player/Player.png");
            sprite.registerAnimation("idle",new Animation(16,16,128,256,0,4,200));
            sprite.setAnimation("idle");
            this.gameModel = gameModel;
	}
	
	@Override
	public void applyPhysics(TileMap tileMap, double dt) {
		Point2D.Double doubleLoc = this.getDoubleLoc();
		if (!onGround(tileMap, doubleLoc) && velocity.y < MAXFALLSPEED)
			velocity.setLocation(velocity.x, velocity.y+GRAVITY*dt);
		walk(dt);
		if (velocity.x * walkingDirection <= 0 ) {
			if (Math.abs(velocity.x) > FRICTIONMINSPEED)
				velocity.x *= FRICTIONFACTOR;
			else
				velocity.x = 0;
		}
		Point2D.Double nextLoc = nextLoc(dt);
		/*if (velocity.x > 0) {
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
		}*/
		if (velocity.y > 0) {
			if (onGround(tileMap,nextLoc)) {
				this.velocity.y = 0;
				nextLoc = new Point2D.Double(doubleLoc.x,Math.ceil(doubleLoc.y));
				while (!onGround(tileMap,nextLoc))
					nextLoc.y += 1.0;
			}
		}
		else if (velocity.y < 0) {
			if (onRoof(tileMap,nextLoc)) {
				this.velocity.y = 0;
				nextLoc = new Point2D.Double(doubleLoc.x,Math.ceil(doubleLoc.y));
				while (!onRoof(tileMap,nextLoc))
					nextLoc.y -= 1.0;
			}
		}

		this.setDoubleLoc(nextLoc);
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

	@Override
	public void die() {
		//System.out.println(this.toString()+" est mort!");
		//System.exit(1);
	}
	
	private void jump() {
		this.velocity.y = -JUMPSTRENGTH;
		this.gameModel.getAudio().play("jump");
	}

	public void conditionalJumpFunctionToJumpOnlyOnGround(TileMap tileMap) {
		if (onGround(tileMap,this.getDoubleLoc())) this.jump();
	}

	public void applyColisions(){
		for(Entity e:this.gameModel.getEntities()){
			Rectangle inter = this.getBounds().intersection(e.getBounds());
			if (!inter.isEmpty() && e!=this && e.isColisionable()) {
				//System.out.println("Colision avec "+e.toString());
				if (inter.height<inter.width && this.getLoc().y<e.getLoc().y) e.die(); else this.die();
			}
		}
	}

	public void walk(double dt) {
		System.out.println(this.walkingDirection);
		double factor = (1 + SPRINTFACTOR*(this.isSprinting? 1 : 0)) ;
		this.velocity.x = this.velocity.x + WALKSTRENGTH * factor * dt * this.walkingDirection;
		double absv = Math.abs(velocity.x);
		if (Math.abs(this.velocity.x) > MAXWALKSPEED * factor) this.velocity.x = MAXWALKSPEED * factor * this.walkingDirection;
	}

	public void startWalk(int walkingDirection, Boolean isSprinting) {
		this.walkingDirection = walkingDirection;
		this.isSprinting = isSprinting;
	}

	public void stopWalk() {
		this.walkingDirection = 0;
	}
}
