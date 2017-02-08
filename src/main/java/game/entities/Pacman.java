package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.OBJLoader;
import org.joml.Vector3f;

/**
 * Created by fritz on 2/8/17.
 */
public class Pacman extends Entity {

    private float speed;

    public Pacman() throws Exception {
        super(null);

        this.speed = .2f;

        this.model = OBJLoader.loadModel("/models/pacman.obj");
        Material material = new Material(new Vector3f(1f, 1f, 0f), 1);

        this.model.setMaterial(material);
        this.setScale(0.5f);
        this.setPosition(0, 0, -2);
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public Vector3f getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(float x, float y, float z) {
        super.setPosition(x, y, z);
    }

    @Override
    public float getScale() {
        return super.getScale();
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
    }

    @Override
    public Vector3f getRotation() {
        return super.getRotation();
    }

    @Override
    public void setRotation(float x, float y, float z) {
        super.setRotation(x, y, z);
    }
}
