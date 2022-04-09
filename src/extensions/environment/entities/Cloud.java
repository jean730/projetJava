package extensions.environment.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.ui.Sprite;
import extensions.environment.ui.Animation;
import extensions.environment.TileMap;

public class Cloud extends Entity {

        private double speed;
        private double x;

	public Cloud(Point2D.Double loc,int speed,String fileName) {
            super(loc);
            x = getLoc().x;
            this.sprite = new Sprite(fileName);
            this.speed = speed;
	}

        public void applyPhysics(TileMap tileMap, double dt){
            x = x+speed*dt;
            if(x+sprite.getWidth()<-240)
                x=480;
            setDoubleLoc(new Point2D.Double(x,getDoubleLoc().y));
        }
        public void die(){};
}
