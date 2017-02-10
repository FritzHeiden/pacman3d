package game.scenes;

import engine.Window;

/**
 * Created by kilian on 09.02.17.
 */
public class WonScene extends MenuScene {

    @Override
    public void init(Window window) throws Exception {
        this.setLogoFileName("/textures/won.png");
        super.init(window);
    }
}
