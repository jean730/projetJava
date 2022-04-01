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

    }
}
