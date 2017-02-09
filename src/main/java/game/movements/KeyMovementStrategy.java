package game.movements;

import engine.Entity;
import engine.Window;
import game.entities.Node;
import game.level.Level;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

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
        System.out.println(this.getDirection());

        if (this.getMovingObject().getPosition().equals(this.getTargetPosition())) {
            this.setCurrentNode(this.getTarget());

            if (this.getDirection().equals(this.UP)  && this.getCurrentNode().hasUpperNeighbor()) {
                this.setTarget(this.getCurrentNode().getUpperNeighbor());
                System.out.println("up");
            } else if (this.getDirection().equals(this.DOWN) && this.getCurrentNode().hasLowerNeighbor()) {
                this.setTarget((this.getCurrentNode().getLowerNeighbor()));
                System.out.println("down");
            } else if (this.getDirection().equals(this.LEFT)  && this.getCurrentNode().hasLeftNeighbor()) {
                this.setTarget((this.getCurrentNode().getLeftNeighbor()));
                System.out.println("left");
            } else if (this.getDirection().equals(this.RIGHT)  && this.getCurrentNode().hasRightNeigbor()) {
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
            this.setDirection(this.UP);
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            this.setTarget(this.getCurrentNode().getLowerNeighbor());
            this.setDirection(this.DOWN);
        }

    }

    public boolean directionUpDown() {
        return this.getDirection() == this.UP || this.getDirection() == this.DOWN ||
                this.getMovingObject().getPosition() == this.getTargetPosition();
    }

}
