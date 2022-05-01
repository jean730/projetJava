package extensions.environment;
import libraries.SimplexNoise;
import java.awt.geom.Point2D;
import java.util.Random;
import extensions.environment.GameModel;
import extensions.environment.entities.StaticEntity;


public class TreeGenerator {
    private static int getHeight(int x,int height){
            return height-(int)(10*SimplexNoise.noise(x/30.0,0)+15);
    }
    public static void generate(GameModel model){
        Random r = new Random(2);
        int[][] map = model.getTileMap().getTextureMap();
        for(int x=0;x<map[0].length;x++){
            int h = getHeight(x,map.length);
            int h_left = getHeight(x-1,map.length);
            int h_right = getHeight(x+1,map.length);
            if(r.nextInt(100)<30 && h==h_left && h==h_right){
                StaticEntity ent = new StaticEntity(new Point2D.Double(16*x-32,-80+16*h),"assets/GrassLand/Details/GrassLand_Tree.png");
                model.addEntity(ent);
            }
        }
    }
}
