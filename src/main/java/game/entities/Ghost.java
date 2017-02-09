package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;
import game.movements.AbstractMovement;
import game.movements.AutoMovementStrategy;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Ghost extends Entity {
    public static final int RED = 0, ORANGE = 1, PINK = 2, TURQUOISE = 3;

    private int color;
    private float speed;
    private AbstractMovement movementStrategy;

    public Ghost(int color, Node node) throws Exception {
        super();

        this.movementStrategy = new AutoMovementStrategy(node, this);
        this.model = OBJLoader.loadModel("/models/ghost.obj");

        this.speed = .2f;
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
        this.setScale(0.2f);
        this.setPosition(node.getPosition());
    }

    @Override
    public void update() {
        this.movementStrategy.move();
    }

    @Override
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        Vector3f offset = new Vector3f(offsetX, offsetY, offsetZ);
        if (offset.length() > 0) {
            offset = offset.normalize();
            offset.mul(speed);
        }
        super.movePosition(offset.x, offset.y, offset.z);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

//    @Override
//    public Vector3f getPosition() {
//        return super.getPosition();
//    }

    @Override
    public void setPosition(float x, float y, float z) {
        super.setPosition(x, y, z);
    }

    @Override
    public float getScale() {
        return super.getScale();
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
    }
//
//    @Override
//    public Vector3f getRotation() {
//        return super.getRotation();
//    }

    @Override
    public void setRotation(float x, float y, float z) {
        super.setRotation(x, y, z);
    }
}
