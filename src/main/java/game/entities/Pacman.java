package game.entities;

import engine.Entity;
import engine.Window;
import engine.graph.Material;
import engine.graph.OBJLoader;
import game.movements.AbstractMovement;
import game.movements.KeyMovementStrategy;
import game.scenes.GameScene;
import org.joml.Vector3f;

import java.util.List;

/**
 * Created by fritz on 2/8/17.
 */
public class Pacman extends Entity {

    private float speed;
    private KeyMovementStrategy movementStrategy;
    private GameScene gameScene;

    public Pacman(Node node, Window window, GameScene gameScene) throws Exception {
        super();
        this.gameScene = gameScene;
        this.movementStrategy = new KeyMovementStrategy(node, this, window);
        this.speed = .20f;

        this.model = OBJLoader.loadModel("/models/pacman.obj");
        Material material = new Material(new Vector3f(1f, 1f, 0f), 1);

        this.model.setMaterial(material);
        this.setScale(0.2f);
        this.setPosition(node.getPosition());
    }

    private int eatBreadcrumps() {
        List<Breadcrump> breadcrumpList = this.gameScene.getLevel().getBreadcrumpList();
        for (int i = 0; i < breadcrumpList.size(); i++) {
            if (this.getPosition().closeTo(breadcrumpList.get(i).getPosition())) {
                breadcrumpList.remove(breadcrumpList.get(i));
                return 5;
            }
        }
        return 0;
    }

    @Override
    public int update() {
        this.movementStrategy.move();
        return this.eatBreadcrumps();
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

    public void die() {
        this.movementStrategy.die();
        this.gameScene.kill();
    }

    public boolean backToLife() {
        return this.movementStrategy.backToLife();
    }
}
