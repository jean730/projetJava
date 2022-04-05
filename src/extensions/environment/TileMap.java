package extensions.environment;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;

public class TileMap extends Shape{

	public final int TILEPERROW = 16;
	public final int TILEWIDTH;
	
	private BufferedImage tileSet;
	private int[][] textureMap;

	public TileMap(String path, int[][] map){
		try {
			this.tileSet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.textureMap=map;
		this.TILEWIDTH = this.tileSet.getWidth()/TILEPERROW;
	}
	
	public BufferedImage getTileSet() {
		return tileSet;
	}

	public int[][] getTextureMap() {
		return textureMap;
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
		((EnvironmentVisitor) visitor).visitTileMap(this);
	}
	
}
