package game.movements;

import engine.Entity;
import game.entities.Node;
import game.entities.Vector3f;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by kilian on 08.02.17.
 */
public abstract class AbstractMovement {

    public final Vector3f UP = new Vector3f(.0000f, .0000f, -.01f);
    public final Vector3f DOWN = new Vector3f (.0000f, .0000f, .01f);
    public final Vector3f RIGHT = new Vector3f (.01f, .0000f, .0000f);
    public final Vector3f LEFT = new Vector3f (-.01f, .0000f, .0000f);
    public final Vector3f STOP = new Vector3f (.0000f, .0000f, .0000f);
    private final Map<String, Vector3f> directions = new HashMap<>();

    private Node currentNode;
    private Entity movingObject;
    private Node nextNode;
    private Vector3f direction;

    public AbstractMovement(Node node, Entity movingObject) {
        this.currentNode = node;
        this.nextNode = node;
        this.movingObject = movingObject;
        this.direction = this.RIGHT;

        directions.put("UP", this.UP);
        directions.put("DOWN", this.DOWN);
        directions.put("RIGHT", this.RIGHT);
        directions.put("LEFT", this.LEFT);
    }

    public void move() {
        this.movingObject.movePosition(this.direction);
        this.colorTargetNode();
    }

    public void colorTargetNode() {
        this.getCurrentNode().getBlock().setColor(new Vector3f(0, 0, 1));
        this.getNextNode().getBlock().setColor(new org.joml.Vector3f(0, 1, 0));
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Entity getMovingObject() {
        return movingObject;
    }

    public Node getNextNode() {
        return nextNode;
    }

    protected void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    protected void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    protected void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Vector3f getNextNodePosition() {
        return nextNode.getBlock().getPosition();

    }

    public Vector3f getDirection() {
        return direction;
    }

    public Vector3f getCurrentPosition() {
        return this.currentNode.getBlock().getPosition();
    }
}
