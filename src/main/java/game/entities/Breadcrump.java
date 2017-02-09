package game.entities;

import engine.Entity;
import game.level.Block;

/**
 * Created by kilian on 08.02.17.
 */
public class Breadcrump extends Entity {

    private Block block;

    public Breadcrump(Block block) throws Exception {
        super(block.getModel());
        this.block = block;
    }
}
