package extensions.environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    private Scanner scanner;
    private TileMap tileMap;

    public Loader(String path){
        try {
            this.scanner=new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String tileSet=this.scanner.next();
        int lines=this.scanner.nextInt();
        int rows=this.scanner.nextInt();
        int[][] map=new int[lines][rows];
        for (int i = 0; i <lines; i++) {
            for (int j = 0; j <rows; j++) {
                map[i][j]=this.scanner.nextInt();
            }
        }
        tileMap=new TileMap(tileSet, map);
        this.scanner.close();
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
