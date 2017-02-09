package game.movements;

import engine.Entity;
import engine.Window;
import game.entities.Node;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by kilian on 08.02.17.
 */
public class KeyMovementStrategy extends AbstractMovement {

    private Window window;
    private String directionString;

    public KeyMovementStrategy(Node node, Entity movingObject, Window window) {
        super(node, movingObject);
        this.directionString = "";
        this.window = window;
    }

    public void move() {
        this.opositeDirection();
        if (this.getMovingObject().getPosition().closeTo(this.getNextNodePosition())) {
            this.setCurrentNode(this.getNextNode());
            this.keyContinuous();

            if (this.getDirection().closeTo(this.UP) && this.getCurrentNode().hasUpperNeighbor()) {
                this.setNextNode(this.getCurrentNode().getUpperNeighbor());
            } else if (this.getDirection().closeTo(this.DOWN) && this.getCurrentNode().hasLowerNeighbor()) {
                this.setNextNode((this.getCurrentNode().getLowerNeighbor()));
            } else if (this.getDirection().closeTo(this.LEFT) && this.getCurrentNode().hasLeftNeighbor()) {
                this.setNextNode((this.getCurrentNode().getLeftNeighbor()));
            } else if (this.getDirection().closeTo(this.RIGHT) && this.getCurrentNode().hasRightNeigbor()) {
                this.setNextNode(this.getCurrentNode().getRightNeighbor());
            } else {
                this.setDirection(this.STOP);
            }
        }
        super.move();

    }

    public void keyContinuous() {
        if (this.window.isKeyPressed(GLFW_KEY_UP)) {
            if (this.getCurrentNode().hasUpperNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getUpperNeighbor());
                this.setDirection(this.UP);
                this.directionString = "UP";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.getCurrentNode().hasLowerNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getLowerNeighbor());
                this.setDirection(this.DOWN);
                this.directionString = "DOWN";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_RIGHT) && this.directionLeftRight()) {
            if (this.getCurrentNode().hasRightNeigbor()) {
                this.setNextNode(this.getCurrentNode().getRightNeighbor());
                this.setDirection(this.RIGHT);
                this.directionString = "RIGHT";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_LEFT)  && this.directionLeftRight()) {
            if (this.getCurrentNode().hasLeftNeighbor()) {
                this.setNextNode(this.getCurrentNode().getLeftNeighbor());
                this.setDirection(this.LEFT);
                this.directionString = "LEFT";
            }
        }
    }

    private void opositeDirection() {
        if (this.window.isKeyPressed(GLFW_KEY_UP)) {
            if (this.directionString.equals("DOWN")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(this.UP);
                this.directionString = "UP";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.directionString.equals("UP")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(this.DOWN);
                this.directionString = "DOWN";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_LEFT)) {
            if (this.directionString.equals("RIGHT")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(this.LEFT);
                this.directionString = "LEFT";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_RIGHT)) {
            if (this.directionString.equals("LEFT")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(this.RIGHT);
                this.directionString = "RIGHT";
            }
        }
    }

    private boolean directionUpDown() {
        return this.getDirection().closeTo(this.UP) || this.getDirection().closeTo(this.DOWN) ||
                this.getMovingObject().getPosition().closeTo(this.getNextNodePosition());
    }

    private boolean directionLeftRight() {
        return this.getDirection().closeTo(this.LEFT) || this.getDirection().closeTo(this.RIGHT) ||
                this.getMovingObject().getPosition().closeTo(this.getNextNodePosition());
    }

}
