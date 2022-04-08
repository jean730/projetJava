package extensions.environment.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.ui.Sprite;
import extensions.environment.ui.Animation;
import extensions.environment.TileMap;

public class StaticEntity extends Entity {
	public StaticEntity(Point2D.Double loc,String fileName) {
            super(loc);
            this.sprite = new Sprite(fileName);
	}

        @Override
        public void applyPhysics(TileMap tileMap, double dt){}
        public void die(){}
}
