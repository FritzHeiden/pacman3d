package game.movements;

import engine.Entity;
import engine.Window;
import game.entities.Node;

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
        super.move();
    }

    public void keyContinuous(Window window) {
        if(window.isKeyPressed(GLFW_KEY_UP)) {
            this.setDirection(this.UP);
            System.out.println("up");
        }

    }

    public boolean directionUpDown() {
        return this.getDirection() == this.UP || this.getDirection() == this.DOWN ||
                this.getMovingObject().getPosition() == this.getTargetPosition();
    }

}
