package game.level;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Block extends Entity {

    private int col;
    private int row;

    public Block(int col, int row) throws Exception {
        super();

        this.col = col;
        this.row = row;

        this.model = OBJLoader.loadModel("/models/cube.obj");
        Material material = new Material(new Vector3f(.2f, .2f, .2f), 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, 0, -2);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    public void setColor(Vector3f color) {
        this.model.setMaterial(new Material(color, 1));
    }
}
