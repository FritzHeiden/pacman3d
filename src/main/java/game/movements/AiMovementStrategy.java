package game.movements;

import engine.Entity;
import game.entities.Ghost;
import game.entities.Node;
import game.entities.Pacman;

import java.util.List;
import java.util.Map;

/**
 * Created by kilian on 10.02.17.
 */
public class AiMovementStrategy extends AutoMovementStrategy {

    private Ghost movingObject;


    public AiMovementStrategy(Node node, Ghost movingObject) {
        super(node, movingObject);
        this.movingObject = movingObject;
    }

    @Override
    public void move() {

        if (this.getMovingObject().getPosition().near(movingObject.getPacman().getPosition()) &&
                this.getMovingObject().getPosition().closeTo(this.getNextNodePosition())) {
            Node lastNode = this.getCurrentNode();
            this.setCurrentNode(this.getNextNode());

            Map<String, Node> possibleTargets = this.getCurrentNode().getNeighborMap();
            possibleTargets.remove(this.getLastNode());

            Object[] key = possibleTargets.keySet().toArray();

            Node tmp;
            Node target = possibleTargets.get((String) key[0]);
            String keyString = (String) key[0];
            for (int i = 0; i < possibleTargets.size(); i++) {

                tmp = possibleTargets.get((String) key[i]);
                if (target.getPosition().distance(this.movingObject.getPacman().getPosition())
                        > tmp.getPosition().distance(this.movingObject.getPacman().getPosition())) {
                    target = tmp;
                    keyString = (String) key[i];
                }
            }
            setMoveDirection(keyString);
            this.setNextNode(target);
            this.superSuperMove();
        } else {
            super.move();
        }
    }
}


