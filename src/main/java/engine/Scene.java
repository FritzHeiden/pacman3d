package engine;

public abstract class Scene {

    private Scene requestedScene;

    public abstract void init(Window window) throws Exception;

    public abstract void input(Window window, MouseInput mouseInput);

    public abstract void update(float interval, MouseInput mouseInput);

    public abstract void render(Window window);

    public abstract void cleanup();

    public void switchScene(Scene newScene) {
        requestedScene = newScene;
    }

    public Scene getRequestedScene() {
        return requestedScene;
    }
}