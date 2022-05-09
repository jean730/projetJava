package extensions.environment;
import java.util.Random;
import libraries.SimplexNoise;

public class Generator {
  private TileMap tileMap;
  private int getHeight(int x, int height) {
    return height - (int)(10 * SimplexNoise.noise(x / 30.0, 0) + 15);
  }
  public Generator(String tileSet, int width, int height, int xOffset) {
    Random r = new Random();
    int[][] map = new int[height][width];
    for (int x = 0; x < width; x++) {
      int h = getHeight(x + xOffset, height);
      int h_left = getHeight(x + 1 + xOffset, height);
      int h_right = getHeight(x - 1 + xOffset, height);
      for (int y = 0; y < height; y++) {
        if (y < h) {
          map[y][x] = -1;
        } else {
          if (y == h) {
            if (h_left > h && h_right > h) {
              map[y][x] = 14;
              map[y + 1][x] = 15;
            } else if (h_left > h && h_right <= h) {
              map[y][x] = 46;
              for (int y2 = y + 1; y2 <= getHeight(x + xOffset + 1, height);
                   y2++) {
                if (map[y2 - 1][x] == 46 + 16)
                  map[y2 - 1][x] = 46 + 13;
                map[y2][x] = 46 + 16;
              }

            } else if (h_left <= h && h_right > h) {
              map[y][x] = 44;
              for (int y2 = y + 1; y2 <= getHeight(x + xOffset - 1, height);
                   y2++) {
                if (map[y2 - 1][x] == 44 + 16)
                  map[y2 - 1][x] = 44 + 12;
                map[y2][x] = 44 + 16;
              }
            } else
              map[y][x] = 45;
          } else if (map[y][x] ==
                     0) { // verifions qu'il n'a pas été modifié entre temps
            int randint = r.nextInt(10);
            if (randint < 3) {
              switch (randint) {
              case 0:
                map[y][x] = 74;
                break;
              case 1:
                map[y][x] = 58;
                break;
              case 2:
                map[y][x] = 57;
                break;
              }
            } else
              map[y][x] = 73;
          }
        }
      }
    }
    tileMap = new TileMap(tileSet, map);
  }

  public TileMap getTileMap() { return tileMap; }
}
