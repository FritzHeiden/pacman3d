package game.entities;

import engine.Entity;
import engine.Window;
import engine.graph.Material;
import engine.graph.OBJLoader;
import game.movements.AbstractMovement;
import game.movements.KeyMovementStrategy;
import org.joml.Vector3f;

import java.util.List;

/**
 * Created by fritz on 2/8/17.
 */
public class Pacman extends Entity {

    private float speed;
    private AbstractMovement movementStrategy;
    private List<Breadcrump> breadcrumpList;
    private int col, row;


    public Pacman(Node node, Window window, List<Breadcrump> breadcrumpList) throws Exception {
        super();
        this.breadcrumpList = breadcrumpList;
        this.movementStrategy = new KeyMovementStrategy(node, this, window);
        this.speed = .20f;

        this.model = OBJLoader.loadModel("/models/pacman.obj");
        Material material = new Material(new Vector3f(1f, 1f, 0f), 1);

        this.model.setMaterial(material);
        this.setScale(0.08f);
        this.setPosition(node.getPosition());
        this.getPosition().add(0, .17f, 0);
    }

    private void eatBreadcrumps() {
        for (int i = 0; i < this.breadcrumpList.size(); i++) {
            if (this.getPosition().closeTo(breadcrumpList.get(i).getPosition())) {
                System.out.println("remove breadbrump");
                this.breadcrumpList.remove(breadcrumpList.get(i));
            }
        }
    }

    @Override
    public void update() {
        this.movementStrategy.move();
        this.eatBreadcrumps();
    }

    @Override
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        Vector3f offset = new Vector3f(-offsetX, offsetY, -offsetZ);
        if (offset.length() > 0) {
            offset = offset.normalize();
            offset.mul(speed);
        }
        super.movePosition(offset.x, offset.y, offset.z);
    }
}
