package game.scenes;


import engine.Entity;
import engine.MouseInput;
import engine.Scene;
import engine.Window;
import engine.graph.*;
import game.Renderer2D;
import org.joml.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by fritz on 2/8/17.
 */
public class MenuScene extends Scene {
    private Renderer2D renderer;
    private final Vector3f cameraInc;
    private String logoFileName;

    private ArrayList<Entity> entities;

    private static final float CAMERA_POS_STEP = 0.05f;

    public MenuScene() {

        renderer = new Renderer2D();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);

        entities = new ArrayList<>();
        this.logoFileName = "/textures/logo.png";

    }

    public void init(Window window) throws Exception {
        renderer.init(window);

        Model logoModel = OBJLoader.loadModel("/models/squarePlain.obj");
        logoModel.setMaterial(new Material(new Texture(logoFileName), 1));
        Entity logoEntity = new Entity(logoModel);
        logoEntity.setScale(1.20f);
        logoEntity.setPosition(0, .2f, 0);
        entities.add(logoEntity);

        Model startModel = OBJLoader.loadModel("/models/squarePlain.obj");
        startModel.setMaterial(new Material(new Texture("/textures/start.png"), 1));
        Entity startEntity = new Entity(startModel);
        startEntity.setScale(.30f);
        startEntity.setPosition(0, -.3f, 0);
        entities.add(startEntity);

        Model quitModel = OBJLoader.loadModel("/models/squarePlain.obj");
        quitModel.setMaterial(new Material(new Texture("/textures/quit.png"), 1));
        Entity quitEntity = new Entity(quitModel);
        quitEntity.setScale(.30f);
        quitEntity.setPosition(0, -.50f, 0);
        entities.add(quitEntity);
    }

    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }

        if (mouseInput.isLeftButtonPressed()) {
            double x = mouseInput.getCurrentPos().x/window.getWidth(), y = mouseInput.getCurrentPos().y/window.getHeight();
            float offsetX = .5f;
            float offsetY = .65f;
            if (x > -.085f + offsetX && x < .085f + offsetX && y > -.05f + offsetY && y < .05f + offsetY) {
                switchScene(new GameScene());
            }

            offsetY = .755f;
            if (x > -.085f + offsetX && x < .085f + offsetX && y > -.025f + offsetY && y < .025f + offsetY) {
                window.close();
            }
        }
    }

    public void update(float interval, MouseInput mouseInput) {
    }

    public void render(Window window) {
        renderer.render(window, entities);
    }

    public void cleanup() {
        renderer.cleanup();
        for (Entity entity : entities) {
            entity.getModel().cleanUp();
        }
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }
}
