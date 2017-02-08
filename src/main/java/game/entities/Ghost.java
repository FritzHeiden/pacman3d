package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Ghost extends Entity {
    public static final int RED = 0, ORANGE = 1, PINK = 2, TURQUOISE = 3;

    private int color;

    public Ghost(int color) throws Exception {
        super(null);

        this.model = OBJLoader.loadModel("/models/ghost.obj");

        this.color = color;
        Vector3f materialColor;

        switch (color) {
            case RED:
                materialColor = new Vector3f(234f/255f, 30f/255f, 31f/255f);
                break;
            case ORANGE:
                materialColor = new Vector3f(247f/255f, 158f/255f, 4f/255f);
                break;
            case PINK:
                materialColor = new Vector3f(252f/255f, 172f/255f, 199f/255f);
                break;
            case TURQUOISE:
                materialColor = new Vector3f(83f/255f, 221f/255f, 208f/255f);
                break;
            default:
                throw new Exception("Unknown color!");
        }

        Material material = new Material(materialColor, 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, -.5f, -2);
    }
}
