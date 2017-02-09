package game.entities;

import com.sun.org.apache.xpath.internal.operations.Mod;
import engine.Entity;
import engine.graph.Material;
import engine.graph.Model;
import engine.graph.OBJLoader;
import game.level.Block;
import org.joml.*;

/**
 * Created by kilian on 08.02.17.
 */
public class Breadcrump extends Entity {

    public Breadcrump() throws Exception {
        super();

        this.model = OBJLoader.loadModel("/models/cube.obj");
        Material material = new Material(new org.joml.Vector3f(.8f, .8f, .1f), 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, 0, -2);
    }
}
