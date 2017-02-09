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

    public KeyMovementStrategy(Node node, Entity movingObject, Window window) {
        super(node, movingObject);
        this.window = window;
    }

    public void move() {
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
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.getCurrentNode().hasLowerNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getLowerNeighbor());
                this.setDirection(this.DOWN);
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_RIGHT)) {
            if (this.getCurrentNode().hasRightNeigbor() && this.directionLeftRight()) {
                this.setNextNode(this.getCurrentNode().getRightNeighbor());
                this.setDirection(this.RIGHT);
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_LEFT)) {
            if (this.getCurrentNode().hasLeftNeighbor() && this.directionLeftRight()) {
                this.setNextNode(this.getCurrentNode().getLeftNeighbor());
                this.setDirection(this.LEFT);
            }
        }
    }

    public boolean directionUpDown() {
        return this.getDirection().closeTo(this.UP) || this.getDirection().closeTo(this.DOWN) ||
                this.getMovingObject().getPosition().closeTo(this.getNextNodePosition());
    }

    public boolean directionLeftRight() {
        return this.getDirection().closeTo(this.LEFT) || this.getDirection().closeTo(this.RIGHT) ||
                this.getMovingObject().getPosition().closeTo(this.getNextNodePosition());
    }

}
