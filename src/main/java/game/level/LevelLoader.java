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

        for (int row = 0; row < lines.size(); row ++) {
            String line = lines.get(row).replaceAll(" ", "");
            for (int col = 0; col < line.length(); col ++) {
                switch(line.charAt(col)) {
                    case '0':
                        level[col][row] = Level.VOID;
                        break;
                    case '+':
                        level[col][row] = Level.NODE;
                        break;
                    case '|':
                        level[col][row] = Level.CONNECTOR_ROW;
                        break;
                    case '-':
                        level[col][row] = Level.CONNECTOR_COL;
                        break;
                }
            }
        }

        return new Level(level);
    }
}
