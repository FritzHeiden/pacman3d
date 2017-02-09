package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.Model;
import engine.graph.OBJLoader;
import game.level.Block;
import org.joml.Vector3d;
import org.joml.Vector3f;

/**
 * Created by kilian on 08.02.17.
 */
public class Node {

    private Block block;
    private Node leftNeighbor;
    private Node rightNeighbor;
    private Node upperNeighbor;
    private Node lowerNeighbor;

    public Node(Block block) throws Exception {
        this.block = block;
    }

    public void setNeighbors(Node node, String position) {
        switch (position) {
            case "left":
                this.setLeftNeighbor(node, true);
                break;
            case "right":
                this.setRightNeighbor(node, true);
                break;
            case "up":
                this.setUpperNeighbor(node, true);
                break;
            case "down":
                this.setLowerNeighbor(node, true);
                break;
        }
    }

    public void setLeftNeighbor(Node leftNeighbor, Boolean firstTime) {
        this.leftNeighbor = leftNeighbor;
        if (firstTime)
            leftNeighbor.setRightNeighbor(this, false);
    }

    public void setLeftNeighbor(Node leftNeighbor) {
        this.setLeftNeighbor(leftNeighbor, false);
    }

    public void setRightNeighbor(Node rightNeighbor, Boolean firstTime) {
        this.rightNeighbor = rightNeighbor;
        if (firstTime)
            rightNeighbor.setLeftNeighbor(this, false);
    }

    public void setRightNeighbor(Node rightNeighbor) {
        this.setRightNeighbor(rightNeighbor, false);
    }

    public void setUpperNeighbor(Node upperNeighbor, boolean firstTime) {
        this.upperNeighbor = upperNeighbor;
        if (firstTime)
            upperNeighbor.setLowerNeighbor(this, false);
    }

    public void setUpperNeighbor(Node upperNeighbor) {
        this.upperNeighbor.setUpperNeighbor(upperNeighbor, false);
    }

    public void setLowerNeighbor(Node lowerNeighbor, boolean firstTime) {
        this.lowerNeighbor = lowerNeighbor;
        if (firstTime)
            lowerNeighbor.setUpperNeighbor(this, false);
    }

    public boolean hasUpperNeighbor()  {
        return this.upperNeighbor != null;
    }

    public boolean hasLowerNeighbor() {
        return this.lowerNeighbor != null;
    }

    public boolean hasLeftNeighbor() {
        return this.leftNeighbor != null;
    }

    public Node getLeftNeighbor() {
        return leftNeighbor;
    }

    public Node getRightNeighbor() {
        return rightNeighbor;
    }

    public Node getUpperNeighbor() {
        return upperNeighbor;
    }

    public Node getLowerNeighbor() {
        return lowerNeighbor;
    }

    public boolean hasRightNeigbor() {
        return this.rightNeighbor != null;

    }

    public void setLowerNeighbor(Node lowerNeighbor) {
        this.lowerNeighbor.setLowerNeighbor(lowerNeighbor, false);
    }

    public int getRow() {
        return this.block.getRow();
    }

    public int getCol(){
        return this.block.getCol();
    }

    public Block getBlock() {
        return block;
    }
}
