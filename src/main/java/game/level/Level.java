package game.level;

import org.joml.Vector3f;

import java.util.ArrayList;

/**
 * Created by fritz on 2/8/17.
 */
public class Level {
    public static final int VOID = 0, PATH = 1, INTERSECTION = 2;

    private int[][] level;
    private ArrayList<Block> blocks;
    private int width, height;

    public Level(int width, int height) throws Exception {
        this(new int[width][height]);
    }

    public Level(int[][] level) throws Exception {
        this.level = level;
        this.width = level.length;
        this.height = level[0].length;
        initializeBlocks();
    }

    private void initializeBlocks() throws Exception {
        this.blocks = new ArrayList<>();
        float scale = .2f;
        float levelHeight = -2f;
        for (int y = 0; y < height; y ++) {
            for (int x = 0; x < width; x ++) {
                Block block = new Block();
                block.setScale(scale/2f);
                switch (level[x][y]) {
                    case VOID:
                        block.setPosition(x * scale, levelHeight - scale, y * scale);
                        block.setColor(new Vector3f(1, 0, 0));
                        break;
                    case PATH:
                    case INTERSECTION:
                        block.setPosition(x * scale, levelHeight, y * scale);
                        block.setColor(new Vector3f(0, 1, 0));
                        break;
                }
                this.blocks.add(block);
            }
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getTile(int x, int y) {
        return level[x][y];
    }

    public void setTile(int x, int y, int type) {
        switch(type) {
            case VOID:
            case PATH:
            case INTERSECTION:
                this.level[x][y] = type;
                break;
            default:
                return;
        }
    }

    public int[][] getLevel() {
        return level;
    }

    public void setLevel(int[][] level) {
        this.level = level;
    }
}
