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

    private int moveUpButton, moveDownButton, moveLeftButton, moveRightButton;
    private boolean downButtonDown;

    public KeyMovementStrategy(Node node, Entity movingObject, Window window) {
        super(node, movingObject);
        this.directionString = "";
        this.window = window;
        this.moveUpButton = GLFW_KEY_UP;
        this.moveDownButton = GLFW_KEY_DOWN;
        this.moveLeftButton = GLFW_KEY_LEFT;
        this.moveRightButton = GLFW_KEY_RIGHT;
    }

    public void move() {
        System.out.println(this.getDirection());
        System.out.println(this.getMovingObject().getPosition());
//        this.oppositeDirection();
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
            } else if (!this.getDirection().closeTo(this.FALL)) {
                this.setDirection(this.STOP);
            }
        }


    }

    public void keyContinuous() {
        if (this.window.isKeyPressed(moveUpButton)) {
            if (this.getCurrentNode().hasUpperNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getUpperNeighbor());
                this.setDirection(this.UP);
                this.directionString = "UP";
            }
        } else if (this.window.isKeyPressed(moveDownButton)) {
            if (this.getCurrentNode().hasLowerNeighbor() && this.directionUpDown()) {
                this.setNextNode(this.getCurrentNode().getLowerNeighbor());
                this.setDirection(this.DOWN);
                this.directionString = "DOWN";
            }
        } else if (this.window.isKeyPressed(moveRightButton) && this.directionLeftRight()) {
            if (this.getCurrentNode().hasRightNeigbor()) {
                this.setNextNode(this.getCurrentNode().getRightNeighbor());
                this.setDirection(this.RIGHT);
                this.directionString = "RIGHT";
            }
        } else if (this.window.isKeyPressed(moveLeftButton) && this.directionLeftRight()) {
            if (this.getCurrentNode().hasLeftNeighbor()) {
                this.setNextNode(this.getCurrentNode().getLeftNeighbor());
                this.setDirection(this.LEFT);
                this.directionString = "LEFT";
            }
        }
    }

//    private void oppositeDirection() {
//        if (this.window.isKeyPressed(moveUpButton)) {
//            if (this.directionString.equals("DOWN")) {
//                this.setNextNode(this.getCurrentNode());
//                this.setDirection(this.UP);
//                this.directionString = "UP";
//            }
//        } else if (this.window.isKeyPressed(moveDownButton)) {
//            if (this.directionString.equals("UP")) {
//                this.setNextNode(this.getCurrentNode());
//                this.setDirection(this.DOWN);
//                this.directionString = "DOWN";
//            }
//        }
////        else if (this.window.isKeyPressed(moveLeftButton)) {
////            if (this.directionString.equals("RIGHT")) {
////                    this.setNextNode(this.getCurrentNode());
////                    this.setDirection(this.LEFT);
////                this.directionString = "LEFT";
////            }
////        } else if (this.window.isKeyPressed(moveRightButton)) {
////            if (this.directionString.equals("LEFT")) {
////                this.setNextNode(this.getCurrentNode());
////                this.setDirection(this.RIGHT);
////                this.directionString = "RIGHT";
////            }
////        }
//    }

    @Override
    public void die() {
        this.setDirection(this.FALL);
        this.killPosition = new Vector3f(this.getMovingObject().getPosition());
        this.killPosition.add(0, -5, 0);
    }

    @Override
    public boolean backToLife() {
        System.out.println(this.getMovingObject().getPosition() + " " + this.killPosition);
        if (this.getMovingObject().getPosition().distance(this.killPosition) < .01f) {
            this.setDirection(this.STOP);
            this.getMovingObject().setPosition(this.getNextNodePosition().x, this.getNextNodePosition().y + .17f,
                    this.getNextNodePosition().z);
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

    public int getMoveUpButton() {
        return moveUpButton;
    }

    public void setMoveUpButton(int moveUpButton) {
        this.moveUpButton = moveUpButton;
    }

    public int getMoveDownButton() {
        return moveDownButton;
    }

    public void setMoveDownButton(int moveDownButton) {
        this.moveDownButton = moveDownButton;
    }

    public int getMoveLeftButton() {
        return moveLeftButton;
    }

    public void setMoveLeftButton(int moveLeftButton) {
        this.moveLeftButton = moveLeftButton;
    }

    public int getMoveRightButton() {
        return moveRightButton;
    }

    public void setMoveRightButton(int moveRightButton) {
        this.moveRightButton = moveRightButton;
    }
}
