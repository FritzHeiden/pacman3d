package game.entities;

import engine.Entity;
import engine.Window;
import engine.graph.Material;
import engine.graph.OBJLoader;
import game.movements.AbstractMovement;
import game.movements.KeyMovementStrategy;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Pacman extends Entity {

    private float speed;
    private Node currentNode;
    private AbstractMovement movementStrategy;


    public Pacman() throws Exception {
        super(null);


//        this.currentNode = node;
//        this.movementStrategy = new KeyMovementStrategy(this.currentNode, this, window);
        this.speed = .10f;

        this.model = OBJLoader.loadModel("/models/pacman.obj");
        Material material = new Material(new Vector3f(1f, 1f, 0f), 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);

//        this.setPosition(this.currentNode.getBlock().getPosition());
    }

    public Pacman(Node node, Window window) throws Exception {
        super(null);


        this.currentNode = node;
        this.movementStrategy = new KeyMovementStrategy(this.currentNode, this, window);
        this.speed = .10f;

        this.model = OBJLoader.loadModel("/models/pacman.obj");
        Material material = new Material(new Vector3f(1f, 1f, 0f), 1);

        this.model.setMaterial(material);
        this.setScale(0.2f);
        this.setPosition(this.currentNode.getBlock().getPosition());
    }

    public void update() {
        this.movementStrategy.move();
    }



    @Override
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        Vector3f offset = new Vector3f(-offsetX, offsetY, -offsetZ);
        if (offset.length() > 0) {
            offset = offset.normalize();
            offset.mul(speed);
        }
        super.movePosition(offset.x, offset.y, offset.z);
    }

//    public float getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }

//    @Override
//    public void setPosition(float x, float y, float z) {
//        super.setPosition(x, y, z);
//    }

//    @Override
//    public float getScale() {
//        return super.getScale();
//    }

//    @Override
//    public void setScale(float scale) {
//        super.setScale(scale);
//    }
//
//    @Override
//    public Vector3f getRotation() {
//        return super.getRotation();
//    }
//
//    @Override
//    public void setRotation(float x, float y, float z) {
//        super.setRotation(x, y, z);
//    }
}
