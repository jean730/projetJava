package extensions.environment.entities;
import libraries.SimplexNoise;
import java.awt.geom.Point2D;
import java.util.Random;
import extensions.environment.GameModel;
import extensions.environment.TileMap;
import extensions.environment.ui.Animation;
import extensions.environment.ui.Sprite;
import extensions.environment.entities.StaticEntity;


public class Pixie extends Entity{

        private double time = 0;
        private GameModel model;
	public Pixie(GameModel model){
            super(new Point2D.Double(0,0));
            this.model = model;
            this.sprite = new Sprite("assets/pixie/pixie.png");
            sprite.registerAnimation("idle",new Animation(8,8,32,8,0,4,250));
            sprite.setAnimation("idle");
	}

        public void applyPhysics(TileMap tileMap, double dt){
            time+=dt/3.0;
            int x = (int)(50*SimplexNoise.noise(0,time));
            int y = (int)(50*SimplexNoise.noise(time,0));
            setDoubleLoc(new Point2D.Double(
                x+model.getPlayers().get(0).getLoc().x,
                y+model.getPlayers().get(0).getLoc().y));
        }
        public void die(){};
}
