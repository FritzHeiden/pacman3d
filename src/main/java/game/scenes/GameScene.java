package game.scenes;

import engine.Entity;
import engine.MouseInput;
import engine.Scene;
import engine.Window;
import engine.graph.Camera;
import engine.graph.PointLight;
import game.Renderer;
import game.entities.Ghost;
import game.entities.Pacman;
import game.level.Level;
import game.level.LevelLoader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GameScene implements Scene {

    private static final float MOUSE_SENSITIVITY = 0.2f;
    private final Vector3f cameraInc;
    private final Renderer renderer;
    private final Camera camera;
    private ArrayList<Entity> entities;
    private Vector3f ambientLight;
    private ArrayList<PointLight> pointLights;
    private Level level;
    private static final float CAMERA_POS_STEP = 0.05f;
    private Pacman pacman;

    public GameScene() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);

        this.entities = new ArrayList<>();
        this.pointLights = new ArrayList<>();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        // Load Level
        this.level = LevelLoader.load("/level/maze.txt");

        camera.setPosition(level.getWidth() / 2 * .2f, 4, level.getHeight() / 2 * .2f);
        camera.setRotation(90f, 0, 0);

        // Make entities
        pacman = new Pacman(this.level.getNodeList().get(0), window);
//        pacman.setPosition(level.getWidth() / 2 * .2f, 0, level.getHeight() / 2 * .2f);
        this.entities.add(pacman);

        Ghost redGhost = new Ghost(Ghost.RED);
        redGhost.setPosition(-3f, 0, -4);
        this.entities.add(redGhost);

        Ghost pinkGhost = new Ghost(Ghost.PINK);
        pinkGhost.setPosition(-1f, 0, -4);
        this.entities.add(pinkGhost);

        Ghost orangeGhost = new Ghost(Ghost.ORANGE);
        orangeGhost.setPosition(1f, 0, -4);
        this.entities.add(orangeGhost);

        Ghost turquoiseGhost = new Ghost(Ghost.TURQUOISE);
        turquoiseGhost.setPosition(3f, 0, -4);
        this.entities.add(turquoiseGhost);

        ambientLight = new Vector3f(0.6f, 0.6f, 0.6f);

        Vector3f lightColour = new Vector3f(1, 1, 1);
        Vector3f lightPosition = new Vector3f(camera.getPosition()).add(0, 1.5f, 0);
        float lightIntensity = 1.0f;
        PointLight pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1f);
        pointLight.setAttenuation(att);
        this.pointLights.add(pointLight);
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
        pointLights.get(0).setPosition(camera.getPosition());
        pacman.update();

//        for (Entity entity : entities) {
//            if (entity instanceof Pacman) {
//                entity.movePosition(cameraInc.x, cameraInc.y, cameraInc.z);
//            }
//        }

        // Update camera based on mouse            
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        ArrayList<Entity> allEntities = new ArrayList<>();
        allEntities.addAll(this.entities);
        allEntities.addAll(this.level.getBlockList());
//        allEntities.addAll(this.level.getNodeList());
        renderer.render(window, camera, allEntities, ambientLight, pointLights);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (Entity entity : entities) {
            entity.getModel().cleanUp();
        }
    }

}
