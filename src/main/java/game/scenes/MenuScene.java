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
    private final Camera camera;

    private ArrayList<Entity> entities;

    private static final float CAMERA_POS_STEP = 0.05f;

    public MenuScene() {
        renderer = new Renderer2D();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);

        entities = new ArrayList<>();
    }

    public void init(Window window) throws Exception {
        renderer.init(window);

        camera.setPosition(0, 0, 5);



        Model logoModel = OBJLoader.loadModel("/models/squarePlain.obj");
        logoModel.setMaterial(new Material(new Texture("/textures/logo.png"), 1));
        Entity logoEntity = new Entity(logoModel);
        logoEntity.setScale(360);
        logoEntity.setPosition(0, 100, 0);
        entities.add(logoEntity);

        Model startModel = OBJLoader.loadModel("/models/squarePlain.obj");
        startModel.setMaterial(new Material(new Texture("/textures/start.png"), 1));
        Entity startEntity = new Entity(startModel);
        startEntity.setScale(100);
        startEntity.setPosition(0, -50, 0);
        entities.add(startEntity);

        Model quitModel = OBJLoader.loadModel("/models/squarePlain.obj");
        quitModel.setMaterial(new Material(new Texture("/textures/quit.png"), 1));
        Entity quitEntity = new Entity(quitModel);
        quitEntity.setScale(100);
        quitEntity.setPosition(0, -120, 0);
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
            double x = mouseInput.getCurrentPos().x, y = mouseInput.getCurrentPos().y;
            float offsetX = window.getWidth() / 2;
            float offsetY = window.getHeight() / 2 + 60;
            if (x > -100 + offsetX && x < 100 + offsetX && y > -35 + offsetY && y < 35 + offsetY) {
                switchScene(new GameScene());
            }

            offsetY += 65;
            if (x > -100 + offsetX && x < 100 + offsetX && y > -20 + offsetY && y < 20 + offsetY) {
                System.exit(0);
            }
        }
    }

    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);
    }

    public void render(Window window) {
        renderer.render(window, camera, entities);
    }

    public void cleanup() {
        renderer.cleanup();
        for (Entity entity : entities) {
            entity.getModel().cleanUp();
        }
    }
}
