package game.level;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Block extends Entity {
    public Block() throws Exception {
        super(null);

        this.model = OBJLoader.loadModel("/models/cube.obj");
        Material material = new Material(new Vector3f(.2f, .2f, .2f), 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, 0, -2);
    }

    public void setColor(Vector3f color) {
        this.model.setMaterial(new Material(color, 1));
    }
}
