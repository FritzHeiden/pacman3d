package game.movements;

import engine.Entity;
import game.entities.Node;
import game.level.Level;

import java.util.*;

/**
 * Created by kilian on 09.02.17.
 */
public class AutoMovementStrategy extends AbstractMovement {

    private String lastNode;

    public AutoMovementStrategy(Node node, Entity movingObject) {
        super(node, movingObject);
    }

    @Override
    public void move() {

        if (this.getMovingObject().getPosition().closeTo(this.getNextNodePosition())) {
            Node lastNode = this.getCurrentNode();
            this.setCurrentNode(this.getNextNode());

            Map<String, Node> possibleTargets = this.getCurrentNode().getNeighborMap();
            possibleTargets.remove(this.lastNode);

            Random generator = new Random();
            Object[] keys = possibleTargets.keySet().toArray();
            Object randomKey = keys[generator.nextInt(possibleTargets.size())];


            this.setNextNode(possibleTargets.get(randomKey.toString()));
            this.setMoveDirection((String) randomKey);
        }
        super.move();
    }

    protected void superSuperMove() {
        super.move();
    }

    protected void setMoveDirection(String key) {
        switch (key.toString()) {
            case "LEFT":
                this.setDirection(LEFT);
                this.lastNode = "RIGHT";
                break;
            case "RIGHT":
                this.setDirection(RIGHT);
                this.lastNode = "LEFT";
                break;
            case "UP":
                this.setDirection(UP);
                this.lastNode = "DOWN";
                break;
            case "DOWN":
                this.setDirection(DOWN);
                this.lastNode = "UP";
                break;
        }
    }

    public String getLastNode() {
        return lastNode;
    }

    public void setLastNode(String lastNode) {
        this.lastNode = lastNode;
    }
}
