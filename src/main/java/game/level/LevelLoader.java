package game.level;

import engine.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fritz on 2/8/17.
 */
public class LevelLoader {
    public static Level load(String path) throws Exception {
        List<String> lines = Utils.readAllLines(path);
        ArrayList nodeList = new ArrayList();
        int[][] level = new int[lines.get(0).replaceAll(" ", "").length()][lines.size()];

        for (int y = 0; y < lines.size(); y ++) {
            String line = lines.get(y).replaceAll(" ", "");
            for (int x = 0; x < line.length(); x ++) {
                switch(line.charAt(x)) {
                    case '0':
                        level[x][y] = Level.VOID;
                        break;
                    case '+':
                        level[x][y] = Level.NODE;
                        break;
                    case '|':
                        level[x][y] = Level.CONNECTOR_ROW;
                        break;
                    case '-':
                        level[x][y] = Level.CONNECTOR_COL;
                        break;
                }
            }
        }

        return new Level(level);
    }
}
