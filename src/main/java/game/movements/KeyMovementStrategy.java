package game.movements;

import engine.Entity;
import engine.Window;
import game.entities.Node;
import game.entities.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by kilian on 08.02.17.
 */
public class KeyMovementStrategy extends AbstractMovement {

    private Window window;
    private String directionString;
    private Vector3f killPosition;

    public KeyMovementStrategy(Node node, Entity movingObject, Window window) {
        super(node, movingObject);
        this.directionString = "";
        this.window = window;
    }

    public void move() {
        this.oppositeDirection();
        super.move();
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


    }

    public void keyContinuous() {
        if (this.window.isKeyPressed(GLFW_KEY_UP)) {
            if (this.getCurrentNode().hasUpperNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getUpperNeighbor());
                this.setDirection(getNewDirection(this.UP));
                this.directionString = "UP";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.getCurrentNode().hasLowerNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getLowerNeighbor());
                this.setDirection(getNewDirection(this.DOWN));
                this.directionString = "DOWN";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_RIGHT) && this.directionLeftRight()) {
            if (this.getCurrentNode().hasRightNeigbor()) {
                this.setNextNode(this.getCurrentNode().getRightNeighbor());
                this.setDirection(getNewDirection(this.RIGHT));
                this.directionString = "RIGHT";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_LEFT) && this.directionLeftRight()) {
            if (this.getCurrentNode().hasLeftNeighbor()) {
                this.setNextNode(this.getCurrentNode().getLeftNeighbor());
                this.setDirection(getNewDirection(this.LEFT));
                this.directionString = "LEFT";
            }
        }
    }

    private void oppositeDirection() {
        if (this.window.isKeyPressed(GLFW_KEY_UP)) {
            if (this.directionString.equals("DOWN")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(getNewDirection(this.UP));
                this.directionString = "UP";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.directionString.equals("UP")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(getNewDirection(this.DOWN));
                this.directionString = "DOWN";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_LEFT)) {
            if (this.directionString.equals("RIGHT")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(getNewDirection(this.LEFT));
                this.directionString = "LEFT";
            }
        } else if (this.window.isKeyPressed(GLFW_KEY_RIGHT)) {
            if (this.directionString.equals("LEFT")) {
                this.setNextNode(this.getCurrentNode());
                this.setDirection(getNewDirection(this.RIGHT));
                this.directionString = "RIGHT";
            }
        }
    }

    private Vector3f getNewDirection(Vector3f pressedDirection) {
        return pressedDirection;
//        if (this.getDirection() == this.UP) {
//            if (pressedDirection == this.UP) {
//                return this.UP;
//            } else if (pressedDirection == this.LEFT) {
//                return this.LEFT;
//            } else if (pressedDirection == this.RIGHT) {
//                return this.RIGHT;
//            } else {
//                return this.DOWN;
//            }
//        } else if (this.getDirection() == this.LEFT) {
//            if (pressedDirection == this.UP) {
//                return this.LEFT;
//            } else if (pressedDirection == this.LEFT) {
//                return this.DOWN;
//            } else if (pressedDirection == this.RIGHT) {
//                return this.UP;
//            } else {
//                return this.RIGHT;
//            }
//        } else if (this.getDirection() == this.RIGHT) {
//            if (pressedDirection == this.UP) {
//                return this.RIGHT;
//            } else if (pressedDirection == this.LEFT) {
//                return this.UP;
//            } else if (pressedDirection == this.RIGHT) {
//                return this.DOWN;
//            } else {
//                return this.LEFT;
//            }
//        } else {
//            if (pressedDirection == this.UP) {
//                return this.DOWN;
//            } else if (pressedDirection == this.LEFT) {
//                return this.RIGHT;
//            } else if (pressedDirection == this.RIGHT) {
//                return this.LEFT;
//            } else {
//                return this.UP;
//            }
//
//        }
    }

    @Override
    public void die() {
        this.setDirection(this.FALL);
        this.killPosition = new Vector3f(this.getMovingObject().getPosition());
        this.killPosition.add(0, -5, 0);
    }

    @Override
    public boolean backToLife() {
        System.out.println(this.getMovingObject().getPosition() + " " + this.killPosition);
        if(this.getMovingObject().getPosition().distance(this.killPosition) < .01f) {
            this.setDirection(this.STOP);
            this.getMovingObject().setPosition(this.getCurrentPosition());
            return true;
        }
        return false;
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
