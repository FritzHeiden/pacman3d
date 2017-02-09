package game.level;

import com.sun.istack.internal.NotNull;
import game.entities.Breadcrump;
import game.entities.Node;
import org.joml.Vector3f;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by fritz on 2/8/17.
 */
public class Level {
    public static final int VOID = 0, NODE = 1, CONNECTOR_ROW = 2, CONNECTOR_COL = 3;

    private int[][] level;
    private ArrayList<Block> blockList;
    private ArrayList<Node> nodeList;
    private ArrayList<Breadcrump> breadcrumpList;
    private int width, height;

    public Level(int width, int height) throws Exception {
        this(new int[width][height]);
    }

    public ArrayList<Breadcrump> getBreadcrumpList() {
        return breadcrumpList;
    }

    public Level(int[][] level) throws Exception {
        this.level = level;
        this.width = level.length;
        this.height = level[0].length;
        this.initializeBlocksNodesBreadcrumps();
        this.setNeighbors();
    }

    public Block getBlock(int row, int col) {
        Block r = null;
        for (Block block: blockList) {
            if (block.getRow() == row && block.getCol() == col)
                r = block;
        }
        return r;
    }

    public Node getNode(int col, int row) {
        Node r = null;
        for (Node node: nodeList) {
            int col0 = node.getCol();
            int row0 = node.getRow();
            if (node.getCol() == col && node.getRow() == row) {
                r = node;
            }
        }
        return r;
    }

    private void initializeBlocksNodesBreadcrumps() throws Exception {
        this.blockList = new ArrayList<>();
        this.breadcrumpList = new ArrayList<>();
        this.nodeList = new ArrayList<>();
        float scale = .2f;
        float levelHeight = -2f;
        for (int x = 0; x < this.width; x ++) {
            for (int y = 0; y < this.height; y ++) {
                Block block = new Block(x, y);
                block.setScale(scale/2f);
                switch (this.level[x][y]) {
                    case Level.VOID:
//                        block.setPosition(col * scale, levelHeight - scale, row * scale);
//                        block.setColor(new Vector3f(1, 0, 0));
                        break;
                    case Level.NODE:
                        block.setPosition(x * scale, levelHeight, y * scale);
                        block.setColor(new Vector3f(1, 0, 0));
                        this.nodeList.add(new Node(block));
//                        this.breadcrumpList.add(new Breadcrump(block));
                        break;
                    case Level.CONNECTOR_COL:
                        block.setPosition(x * scale, levelHeight, y * scale);
                        block.setColor(new Vector3f(.9f, 0, 0));
                        break;
                    case Level.CONNECTOR_ROW:
                        block.setPosition(x * scale, levelHeight, y * scale);
                        block.setColor(new Vector3f(.8f, .3f, .3f));
                        break;

                }
                this.blockList.add(block);
            }
        }
    }

    private void setNeighbors() throws Exception {
        int[] trail = {Level.NODE, Level.CONNECTOR_COL, Level.CONNECTOR_ROW};

        HashMap<String, Integer[]> directions = new HashMap<>();
        directions.put("left", new Integer[] {-1, 0});
        directions.put("right", new Integer[] {1, 0});
        directions.put("up", new Integer[] {0, -1});
        directions.put("down", new Integer[] {0, 1});

        for (Node node: nodeList) {
            for (Map.Entry<String, Integer[]> direction: directions.entrySet()) {
                int x, y, xOffset, valX, yOffset, valY;
                x = node.getCol();
                y = node.getRow();

                xOffset = valX = direction.getValue()[0];
                yOffset = valY = direction.getValue()[1];

                try {
                    int except = this.level[x+xOffset][y+yOffset];
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }

                int val = this.level[x+xOffset][y+yOffset];
                int val0 = this.level[x+xOffset][y+yOffset];

                if (IntStream.of(trail).anyMatch(i -> i == val)) {
                    while (val0 == Level.CONNECTOR_COL || val0 == Level.CONNECTOR_ROW) {
                        xOffset += valX;
                        yOffset += valY;
                        try {
                            int except = this.level[x+xOffset][y+yOffset];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                        val0 = this.level[x+xOffset][y+yOffset];

                    }
                    node.setNeighbors(this.getNode(x+xOffset, y+yOffset),
                            direction.getKey());
                }

            }
        }
    }

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public ArrayList<Node> getNodeList(){
        return nodeList;
    }


//    public int getTile(int x, int y) {
//        return level[x][y];
//    }
//
//    public void setTile(int x, int y, int type) {
//        switch(type) {
//            case VOID:
//            case PATH:
//            case NODE:
//                this.level[x][y] = type;
//                break;
//            default:
//                return;
//        }
//    }

    public int[][] getLevel() {
        return level;
    }

    public void setLevel(int[][] level) {
        this.level = level;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
