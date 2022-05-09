package extensions.environment.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import extensions.environment.EnvironmentVisitor;
import extensions.environment.TileMap;
import extensions.environment.ui.Sprite;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;

public abstract class Entity extends Shape {
		
	protected Sprite sprite;
	private Point2D.Double doubleLoc;
	private boolean isColisionable=false;
	private String type;
	private boolean isDead = false;

	
	public Entity(Point2D.Double doubleLoc, Sprite sprite, String type) {
            this.sprite = sprite;
            this.doubleLoc = doubleLoc;
			this.type = type;
	}
	public Entity(Point2D.Double doubleLoc, Sprite sprite, boolean isColisionable, String type){
		this.sprite = sprite;
		this.doubleLoc = doubleLoc;
		this.isColisionable=isColisionable;
		this.type = type;
	}
	
	public Entity(Point2D.Double doubleLoc, String type) {
		this.doubleLoc = doubleLoc;
		this.type = type;
	}

	@Override
	public Point getLoc() {
		return new Point((int)this.doubleLoc.x,(int)this.doubleLoc.y);
	}

	@Override
	public void setLoc(Point point) {
		this.doubleLoc = new Point2D.Double(this.doubleLoc.x,this.doubleLoc.y);
	}
	
	public Point2D.Double getDoubleLoc() {
		return doubleLoc;
	}
	
	public void setDoubleLoc(Point2D.Double doubleLoc) {
		this.doubleLoc = doubleLoc;
	}

	public void DoubleTranslate(Double dx, Double dy) {
		this.doubleLoc.setLocation(this.doubleLoc.x + dx, this.doubleLoc.y + dy);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getLoc().x,this.getLoc().y,sprite.getWidth(),sprite.getHeight());
	}
	
	public void accept(ShapeVisitor visitor) {
		((EnvironmentVisitor) visitor).visitEntity(this);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public abstract void applyPhysics(TileMap tileMap, double dt);

	public boolean isDead() {
		return isDead;
	}
	
	public void die() {
		isDead = true;
	}

	public boolean isColisionable() {
		return isColisionable;
	}

	public void setColisionable(boolean colisionable) {
		isColisionable = colisionable;
	}

	public String getType() {
		return type;
	}
}
