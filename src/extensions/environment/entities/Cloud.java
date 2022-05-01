package extensions.environment.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import extensions.environment.ui.Sprite;
import extensions.environment.ui.Animation;
import extensions.environment.TileMap;
import extensions.environment.GameModel;

public class Cloud extends Entity {

        private double speed;
        private double x;
        private double y;
        private GameModel model;
	public Cloud(GameModel model,Point2D.Double loc,int speed,String fileName) {
            super(loc);
            x = getLoc().x;
            y = getLoc().y;
            this.sprite = new Sprite(fileName);
            this.speed = speed;
            this.model = model;
	}

        public void applyPhysics(TileMap tileMap, double dt){
            x = x+speed*dt;
            if(x+sprite.getWidth()<-240)
                x=480;
            setDoubleLoc(new Point2D.Double(
                x+model.getPlayers().get(0).getLoc().x,
                y));
        }
        public void die(){};
}
