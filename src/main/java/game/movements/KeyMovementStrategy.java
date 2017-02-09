package game.movements;

import engine.Entity;
import engine.Window;
import game.entities.Node;
import game.level.Level;

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

    @Override
    public void move() {
        this.keyContinuous(this.window);
        if (this.getMovingObject().getPosition().closeTo(this.getTargetPosition())) {
            this.setCurrentNode(this.getTarget());

            if (this.getDirection().closeTo(this.UP) && this.getCurrentNode().hasUpperNeighbor()) {
                this.setTarget(this.getCurrentNode().getUpperNeighbor());
                System.out.println("up");
            } else if (this.getDirection().closeTo(this.DOWN) && this.getCurrentNode().hasLowerNeighbor()) {
                this.setTarget((this.getCurrentNode().getLowerNeighbor()));
                System.out.println("down");
            } else if (this.getDirection().closeTo(this.LEFT) && this.getCurrentNode().hasLeftNeighbor()) {
                this.setTarget((this.getCurrentNode().getLeftNeighbor()));
                System.out.println("left");
            } else if (this.getDirection().closeTo(this.RIGHT)  && this.getCurrentNode().hasRightNeigbor()) {
                this.setTarget(this.getCurrentNode().getRightNeighbor());
                System.out.println("right");
            } else {
                System.out.println("stop");
                this.setDirection(this.STOP);
            }
        }
        super.move();
    }

    public void keyContinuous(Window window) {
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            System.out.println();
            if (this.getCurrentNode().hasUpperNeighbor() && this.directionUpDown()) {
                this.setDirection(this.UP);
                this.setTarget(this.getCurrentNode().getUpperNeighbor());
            }
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            if (this.getCurrentNode().hasLowerNeighbor() && this.directionUpDown()) {
                this.setTarget(this.getCurrentNode().getLowerNeighbor());
                this.setDirection(this.DOWN);
            }
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            if (this.getCurrentNode().hasRightNeigbor() && this.directionLeftRight()) {
                this.setTarget(this.getCurrentNode().getRightNeighbor());
                this.setDirection(this.RIGHT);
            }
        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            if (this.getCurrentNode().hasLeftNeighbor() && this.directionLeftRight()) {
                this.setTarget(this.getCurrentNode().getLeftNeighbor());
                this.setDirection(this.LEFT);
            }
        }

    }

    public boolean directionUpDown() {
        return this.getDirection().closeTo(this.UP) || this.getDirection().closeTo(this.DOWN) ||
                this.getMovingObject().getPosition().closeTo(this.getTargetPosition());
    }



    public boolean directionLeftRight() {
        return this.getDirection().closeTo(this.LEFT) || this.getDirection().closeTo(this.RIGHT) ||
                this.getMovingObject().getPosition().closeTo(this.getTargetPosition());
    }

}
