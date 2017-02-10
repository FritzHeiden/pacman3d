package game.scenes;

import engine.Entity;
import engine.MouseInput;
import engine.Scene;
import engine.Window;
import engine.graph.Camera;
import engine.graph.PointLight;
import game.Renderer;
import game.Renderer2D;
import game.entities.Ghost;
import game.entities.Pacman;
import game.hud.HUD;
import game.level.Level;
import game.level.LevelLoader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GameScene extends Scene {

    private static final float MOUSE_SENSITIVITY = 0.3f;
    private final Vector3f cameraInc;
    private final Renderer renderer;
    private final Renderer2D renderer2D;
    private final Camera camera;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> entities2d;
    private Vector3f ambientLight;
    private ArrayList<PointLight> pointLights;
    private Level level;
    private HUD hud;
    private static final float CAMERA_POS_STEP = 0.05f;
    private Pacman pacman;
    private Integer score;
    private Integer lives;

    public GameScene() {
        renderer = new Renderer();
        renderer2D = new Renderer2D();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);

        this.entities = new ArrayList<>();
        this.entities2d = new ArrayList<>();
        this.pointLights = new ArrayList<>();
        this.lives = 3;
        this.score = 0;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        renderer2D.init(window);

        // Load Level
        this.level = LevelLoader.load("/level/maze.txt");

        camera.setPosition(level.getWidth() / 2 * .2f, 4, level.getHeight() / 2 * .2f);
        camera.setRotation(30f, 180, 0);

        // Make entities
        pacman = new Pacman(this.level.getNodeList().get(0), window, this);
        pacman.setRotation(0, 0, 0);
        this.entities.add(pacman);

        Ghost redGhost = new Ghost(Ghost.RED, this.level.getNodeList().get(1), this.pacman);
//        redGhost.setPosition(-3f, 0, -4);
        this.entities.add(redGhost);

        Ghost pinkGhost = new Ghost(Ghost.PINK, this.level.getNodeList().get(8), this.pacman);
//        pinkGhost.setPosition(-1f, 0, -4);
        this.entities.add(pinkGhost);
//
        Ghost orangeGhost = new Ghost(Ghost.ORANGE, this.level.getNodeList().get(15), this.pacman);
//        orangeGhost.setPosition(1f, 0, -4);
        this.entities.add(orangeGhost);
//
        Ghost turquoiseGhost = new Ghost(Ghost.TURQUOISE, this.level.getNodeList().get(20), this.pacman);
//        turquoiseGhost.setPosition(3f, 0, -4);
        this.entities.add(turquoiseGhost);

        ambientLight = new Vector3f(0.6f, 0.6f, 0.6f);

        Vector3f lightColour = new Vector3f(1, 1, 1);
        Vector3f lightPosition = new Vector3f(camera.getPosition()).add(0, 1.5f, 0);
        float lightIntensity = 1.0f;
        PointLight pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1f);
        pointLight.setAttenuation(att);
        this.pointLights.add(pointLight);


        // 2d stuff
        hud = new HUD(window);
        hud.setScore(51675416);

//        glfwSetInputMode(window.getWindowHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
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

        if (window.isKeyPressed(GLFW_KEY_ESCAPE)) {
            switchScene(new MenuScene());
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
//        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);


        for (Entity entity : entities)
            score += entity.update();
        hud.setScore(this.score);


        // Update camera based on mouse            
        if (mouseInput.isRightButtonPressed()) {
        Vector2f rotVec = mouseInput.getDisplVec();
        camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);

//        System.out.println(camera.getRotation().y);
        }

        Vector3f direction = new Vector3f(
                (float) Math.cos(Math.toRadians(-90 + camera.getRotation().y)),
                0,
                (float) Math.sin(Math.toRadians(-90 + camera.getRotation().y))).mul(-.6f);
        camera.setPosition(pacman.getPosition());
        camera.getPosition().add(direction);
        camera.getPosition().add(0, .5f, 0);
        pointLights.get(0).setPosition(pacman.getPosition());

        if (this.lives == 0)
            this.switchScene(new MenuScene());
    }


    @Override
    public void render(Window window) {
        ArrayList<Entity> allEntities = new ArrayList<>();
        allEntities.addAll(this.entities);
        allEntities.addAll(this.level.getBlockList());
        allEntities.addAll(this.level.getBreadcrumpList());
//        allEntities.addAll(this.level.getNodeList());
        renderer.render(window, camera, allEntities, ambientLight, pointLights);

        ArrayList<Entity> allEntities2d = new ArrayList<>();
        allEntities2d.addAll(entities2d);
        allEntities2d.addAll(hud.getElements(window));
        renderer2D.render(window, allEntities2d);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (Entity entity : entities) {
            entity.getModel().cleanUp();
        }
        for (Entity entity : level.getBlockList()) {
            entity.getModel().cleanUp();
        }
    }

    public Level getLevel() {
        return level;
    }

    public void kill() {
        this.lives --;
    }


}
