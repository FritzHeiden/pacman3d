package game.hud;

import engine.Entity;
import engine.Window;
import engine.graph.Material;
import engine.graph.Model;
import engine.graph.OBJLoader;
import engine.graph.Texture;

import java.util.ArrayList;

/**
 * Created by fritz on 2/9/17.
 */
public class HUD {

    private ArrayList<Entity> elements;
    private ArrayList<Entity> numbers;

    private int score, live;
    private Window window;

    public HUD(Window window) throws Exception {
        this.score = 0;
        this.live = 3;
        this.elements = new ArrayList<>();
        this.numbers = loadNumbers();
        this.window = window;
        init(window);
    }

    private void init(Window window) throws Exception {
        float width = (float)window.getWidth() / window.getHeight();
        float height = 1;
        Model scoreLabelModel = OBJLoader.loadModel("/models/squarePlain.obj");
//        Model liveLabelModel = OBJLoader.loadModel("/models/squarePlain.obj");
        scoreLabelModel.setMaterial(new Material(new Texture("/textures/score.png"), 1));
        Entity scoreLabelEntity = new Entity(scoreLabelModel);
//        Entity liveLabelEntity = new Entity(liveLabelModel);
        float size = 1000/148 * .02f;
        scoreLabelEntity.setScale(size);
//        liveLabelEntity.setScale(size);
        scoreLabelEntity.setPosition(-width + .1f + size, height - .1f, 0);
//        liveLabelEntity.setPosition(0, 0, 0);
        elements.add(scoreLabelEntity);
//        elements.add(liveLabelEntity);

    }

    public ArrayList<Entity> getElements(Window window) {
        float width = (float)window.getWidth() / window.getHeight();
        float height = 1;

        ArrayList<Entity> allElements = new ArrayList<>();
        allElements.addAll(elements);

        String score = String.valueOf(this.score);
        for (int i = 0; i < score.length(); i ++) {
            int number = Integer.parseInt(String.valueOf(score.charAt(i)));
            Entity numberEntity = new Entity(numbers.get(number));
            float scale = .04f;
            float positionX = -width + .1f + scale + 1000/148 * .04f, positionY = height - .1f;
            numberEntity.setScale(scale/2f);
            numberEntity.setPosition(positionX + i*(scale - scale/16f), positionY, 0);
            allElements.add(numberEntity);
        }

        return allElements;
    }

    private ArrayList<Entity> loadNumbers() throws Exception {
        ArrayList<Entity> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            numbers.add(loadNumber(i));
        }
        return numbers;
    }

    private Entity loadNumber(int number) throws Exception {
        Model model = OBJLoader.loadModel("/models/squarePlain.obj");
        model.setMaterial(new Material(new Texture("/textures/" + number + ".png"), 1));
        Entity entity = new Entity(model);
        return entity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLive(int live) {
        this.live = live;
    }
}
