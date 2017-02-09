package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;

/**
 * Created by kilian on 08.02.17.
 */
public class Breadcrump extends Entity {

    public Breadcrump() throws Exception {
        super();

        this.model = OBJLoader.loadModel("/models/sphere.obj");
        Material material = new Material(new org.joml.Vector3f(1, 1, 1), 1);
        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, 1, -2);
    }
}
