package extensions.environment.entities;

import java.awt.*;
import java.awt.geom.Point2D;

import extensions.environment.TileMap;
import extensions.environment.GameModel;
import extensions.environment.ui.Sprite;
import extensions.environment.ui.Animation;

public class Player extends Entity {
	
	private static final double MAXFALLSPEED = 1000;
	
	private static final double MAXWALKSPEED = 300;
	private static final double SPRINTFACTOR = 0.4;

	private static final double GRAVITY = 1000;
	private static final double JUMPSTRENGTH = 400;
	
	private static final double WALKSTRENGTH = 600;
	private static final double FRICTIONFACTOR = 0.9999;
	private static final double FRICTIONMINSPEED = 10;

        private int Left = 0;
        private int Right = 0;
        private int Jumping = 0;
        private int walkingDirection = 0;
 
	private boolean isSprinting = false;
	private Point2D.Double velocity = new Point2D.Double(0,0);
	private GameModel gameModel;
	
	public Player(Point2D.Double loc,GameModel gameModel) {
            super(loc);
            this.sprite = new Sprite("assets/Player/Player.png");
            sprite.registerAnimation("idle",new Animation(16,16,128,256,0,4,140));
            sprite.registerAnimation("left",new Animation(16,16,128,256,8,4,140));
            sprite.registerAnimation("right",new Animation(16,16,128,256,16,4,140));
            sprite.setAnimation("idle");
            this.gameModel = gameModel;
	}
	
	@Override
	public void applyPhysics(TileMap tileMap, double dt) {
        if(Jumping==1)
        	conditionalJumpFunctionToJumpOnlyOnGround(tileMap);
		walk(dt);
		Point2D.Double doubleLoc = this.getDoubleLoc();
		if (!onGround(tileMap, doubleLoc) && velocity.y < MAXFALLSPEED)
			velocity.setLocation(velocity.x, velocity.y+GRAVITY*dt);

		if (velocity.x * walkingDirection <= 0 ) {
			if (Math.abs(velocity.x) > FRICTIONMINSPEED)
				velocity.x *= FRICTIONFACTOR;
			else
				velocity.x = 0;
		}
		Point2D.Double nextLoc = nextLoc(dt);
		if (velocity.x > 0) {
			if (onWallRight(tileMap,nextLoc)) {
				this.velocity.x = 0;
				nextLoc = new Point2D.Double(Math.ceil(doubleLoc.x),nextLoc.y);
				while (!onWallRight(tileMap,nextLoc))
					nextLoc.x += 1.0;
				this.conditionalJumpFunctionToJumpOnlyLeftRight(tileMap);
			}
		}
		else if (velocity.x < 0) {
			if (onWallLeft(tileMap,nextLoc)) {
				this.velocity.x = 0;
				nextLoc = new Point2D.Double(Math.ceil(doubleLoc.x),nextLoc.y);
				while (!onWallLeft(tileMap,nextLoc))
					nextLoc.x -= 1.0;
				this.conditionalJumpFunctionToJumpOnlyLeftRight(tileMap);
			}
		}
		if (velocity.y > 0) {
			if (onGround(tileMap,nextLoc)) {
				this.velocity.y = 0;
				nextLoc = new Point2D.Double(nextLoc.x,Math.ceil(doubleLoc.y));
				while (!onGround(tileMap,nextLoc))
					nextLoc.y += 1.0;
			}
		}
		else if (velocity.y < 0) {
			if (onRoof(tileMap,nextLoc)) {
				this.velocity.y = 0;
				nextLoc = new Point2D.Double(nextLoc.x,Math.ceil(doubleLoc.y));
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
			for(int i = 1; i < this.getSprite().getWidth(); i++)
			{
				if(tileMap.getTextureMap()[(int) ((doubleLoc.y+this.getSprite().getHeight())/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+i)/tileMap.TILEWIDTH)] >= 0)
				{
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}
	
	public Boolean onRoof(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			for(int i = 1; i < this.getSprite().getWidth(); i++)
			{
				if(tileMap.getTextureMap()[(int) (doubleLoc.y/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+i)/tileMap.TILEWIDTH)] >= 0)
				{
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}

	public Boolean onWallLeft(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			for(int i = 1; i < this.getSprite().getHeight(); i++)
			{
				if(tileMap.getTextureMap()[(int) ((doubleLoc.y+i)/tileMap.TILEWIDTH)][(int) (doubleLoc.x/tileMap.TILEWIDTH)] >= 0)
				{
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			this.die();
			return true;
		}
	}

	public Boolean onWallRight(TileMap tileMap, Point2D.Double doubleLoc)
	{
		try {
			for(int i = 1; i < this.getSprite().getHeight(); i++)
			{
				if(tileMap.getTextureMap()[(int) ((doubleLoc.y+i)/tileMap.TILEWIDTH)][(int) ((doubleLoc.x+this.getSprite().getWidth())/tileMap.TILEWIDTH)] >= 0)
				{
					return true;
				}
			}
			return false;
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

	public void conditionalJumpFunctionToJumpOnlyLeftRight(TileMap tileMap) {
		if (onWallLeft(tileMap,this.getDoubleLoc()) && !onGround(tileMap,this.getDoubleLoc()) && Jumping == 1)
		{
			this.jump();
			this.velocity.x += 300;
		}
		if (onWallRight(tileMap,this.getDoubleLoc()) && !onGround(tileMap,this.getDoubleLoc()) && Jumping == 1)
		{
			this.jump();
			this.velocity.x += -300;
		}
	}
	
	public void conditionalJumpFunctionToJumpOnlyOnGround(TileMap tileMap) {
		if (onGround(tileMap,this.getDoubleLoc())) this.jump();
	}

	public void applyColisions(){
		for(Entity e:this.gameModel.getEntities()){
			Rectangle inter = this.getBounds().intersection(e.getBounds());
			if (!inter.isEmpty() && e!=this && e.isColisionable()) {
				//System.out.println("Collision avec "+e.toString());
				if (inter.height<inter.width && this.getLoc().y<e.getLoc().y) e.die(); else this.die();
			}
		}
	}

	public void walk(double dt) {
                walkingDirection = Right-Left;
                switch(walkingDirection){
                    case(-1):{
                        sprite.setAnimation("left");
                        break;
                    }
                    case(1):{
                        sprite.setAnimation("right");
                        break;
                    }
                    case(0):{
                        sprite.setAnimation("idle");
                        break;
                    }
                }
		double factor = (1 + SPRINTFACTOR*(this.isSprinting? 1 : 0)) ;
		this.velocity.x = this.velocity.x + WALKSTRENGTH * factor * dt * walkingDirection;
		if (Math.abs(this.velocity.x) > MAXWALKSPEED * factor) this.velocity.x = MAXWALKSPEED * factor * walkingDirection;
	}

        public void press(int key){
            switch(key){
                case(0):{
                    Left = 1;
                    break;
                }
                case(1):{
                    Right = 1;
                    break;
                }
                case(2):{
                    Jumping = 1;
                    break;
                }
            }
        }

        public void release(int key){
            switch(key){
                case(0):{
                    Left = 0;
                    break;
                }
                case(1):{
                    Right = 0;
                    break;
                }
                case(2):{
                    Jumping = 0;
                    break;
                }
            }
        }
	
}
