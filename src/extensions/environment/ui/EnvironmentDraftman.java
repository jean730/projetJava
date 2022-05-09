package extensions.environment.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import extensions.environment.EnvironmentVisitor;
import extensions.environment.GameModel;
import extensions.environment.TileMap;
import extensions.environment.entities.Entity;
import graphics.shapes.ui.ShapeDraftman;
import graphics.shapes.SText;

public class EnvironmentDraftman extends ShapeDraftman implements EnvironmentVisitor {

	public EnvironmentDraftman(Graphics g) {
		super(g);
	}
	
	@Override
	public void visitGameModel(GameModel gameModel) {
		gameModel.getTileMap().accept(this);;
		for (Entity entity : gameModel.getEntities()) {
			entity.accept(this);
		}
	}
	
	public void visitTileMap(TileMap tileMap) {
		BufferedImage tileSet = tileMap.getTileSet();
		int[][] textureMap= tileMap.getTextureMap();
		for(int i = 0; i<textureMap.length; i++) {
			for(int j = 0; j<textureMap[i].length; j++) {
				if (textureMap[i][j] != -1) {
					Point tileCoord = new Point(tileMap.TILEWIDTH * (textureMap[i][j]%tileMap.TILEPERROW),tileMap.TILEWIDTH * ((int)textureMap[i][j]/tileMap.TILEPERROW));
					g.drawImage(tileSet, j*tileMap.TILEWIDTH, i*tileMap.TILEWIDTH, tileMap.TILEWIDTH*(1+j) , tileMap.TILEWIDTH*(1+i),tileCoord.x , tileCoord.y, tileCoord.x + tileMap.TILEWIDTH , tileCoord.y + tileMap.TILEWIDTH, null);
				}
			}
		}
	}

	@Override
	public void visitEntity(Entity entity) {
		Sprite sprite = entity.getSprite();
                entity.getSprite().draw(entity.getLoc().x,entity.getLoc().y,g);
	}
}
