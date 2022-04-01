package extensions.environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
    private Scanner scanner;
    private TileMap tileMap;

    Loader(String path){
        try {
            this.scanner=new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String tileSet=this.scanner.next();
        int[][] map={};
        int i=0;
        int j=0;
        while (this.scanner.hasNext()){
            int k=this.scanner.nextInt();
            if (k==-2){
                i++;
                j=0;
                continue;
            }
            map[i][j]=k;
            j++;
        }
        //tileMap=new TileMap(tileSet, map);
    }
}
