package game.level;

import engine.Utils;

import java.util.List;

import static game.level.Level.*;

/**
 * Created by fritz on 2/8/17.
 */
public class LevelLoader {
    public static Level load(String path) throws Exception {
        List<String> lines = Utils.readAllLines(path);
        int[][] level = new int[lines.get(0).replaceAll(" ", "").length()][lines.size()];

        for (int y = 0; y < lines.size(); y ++) {
            String line = lines.get(y).replaceAll(" ", "");
            for (int x = 0; x < line.length(); x ++) {
                switch(line.charAt(x)) {
                    case '0':
                        level[x][y] = VOID;
                        break;
                    case '+':
                        level[x][y] = INTERSECTION;
                        break;
                    case '|':
                    case '-':
                        level[x][y] = PATH;
                        break;
                }
            }
        }

        return new Level(level);
    }
}
