package game.scenes;

import engine.Window;

/**
 * Created by kilian on 09.02.17.
 */
public class GameOverScene extends MenuScene {

    @Override
    public void init(Window window) throws Exception {
        this.setLogoModel("/models/Game_Over.png");
        super.init(window);
    }
}
