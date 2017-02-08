package game;

import engine.Entity;
import engine.Scene;
import engine.MouseInput;
import engine.Window;
import engine.graph.Camera;
import engine.graph.PointLight;
import game.entities.Ghost;
import game.entities.Pacman;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class GameScene implements Scene {

    private static final float MOUSE_SENSITIVITY = 0.2f;
    private final Vector3f cameraInc;
    private final Renderer renderer;
    private final Camera camera;
    private Entity[] entities;
    private Vector3f ambientLight;
    private PointLight[] pointLights;
    private static final float CAMERA_POS_STEP = 0.05f;

    public GameScene() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        // Make entities
        Pacman pacman = new Pacman();
        Ghost redGhost = new Ghost(Ghost.RED);
        Ghost pinkGhost = new Ghost(Ghost.PINK);
        Ghost orangeGhost = new Ghost(Ghost.ORANGE);
        Ghost turquoiseGhost = new Ghost(Ghost.TURQUOISE);

        entities = new Entity[]{pacman, redGhost, pinkGhost, orangeGhost, turquoiseGhost};

        ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
        Vector3f lightColour = new Vector3f(1, 1, 1);
        Vector3f lightPosition = new Vector3f(camera.getPosition()).add(0, 1.5f, 0);
        float lightIntensity = 1.0f;
        PointLight pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1f);
        pointLight.setAttenuation(att);
        pointLights = new PointLight[]{pointLight};
    }

    @Override
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
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        for (Entity entity : entities) {
            if (entity instanceof Pacman) {
                entity.movePosition(cameraInc.x, cameraInc.y, cameraInc.z);
            }
        }

        // Update camera based on mouse            
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, entities, ambientLight, pointLights);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (Entity entity : entities) {
            entity.getModel().cleanUp();
        }
    }

}
