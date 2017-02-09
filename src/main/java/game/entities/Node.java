package game.entities;

import engine.Entity;
import engine.graph.Material;
import engine.graph.Model;
import engine.graph.OBJLoader;
import game.level.Block;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kilian on 08.02.17.
 */
public class Node {

    private Block block;
    private Node leftNeighbor;
    private Node rightNeighbor;
    private Node upperNeighbor;
    private Node lowerNeighbor;
    private Map<String, Node> neighborMap;

    public Node(Block block) throws Exception {
        this.block = block;
        this.neighborMap = new HashMap<>();
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
        this.neighborMap.put("LEFT", this.leftNeighbor);
        if (firstTime)
            leftNeighbor.setRightNeighbor(this, false);
    }

    public void setLeftNeighbor(Node leftNeighbor) {
        this.setLeftNeighbor(leftNeighbor, false);
    }

    public void setRightNeighbor(Node rightNeighbor, Boolean firstTime) {
        this.rightNeighbor = rightNeighbor;
        this.neighborMap.put("RIGHT", this.getRightNeighbor());
        if (firstTime)
            rightNeighbor.setLeftNeighbor(this, false);
    }

    public void setRightNeighbor(Node rightNeighbor) {
        this.setRightNeighbor(rightNeighbor, false);
    }

    public void setUpperNeighbor(Node upperNeighbor, boolean firstTime) {
        this.upperNeighbor = upperNeighbor;
        this.neighborMap.put("UP", this.upperNeighbor);
        if (firstTime)
            upperNeighbor.setLowerNeighbor(this, false);
    }

    public void setUpperNeighbor(Node upperNeighbor) {
        this.upperNeighbor.setUpperNeighbor(upperNeighbor, false);
    }

    public Map<String, Node> getNeighborMap() {
        Map<String, Node> map = new HashMap<>();

        if (this.leftNeighbor != null)
            map.put("LEFT", this.leftNeighbor);
        if (this.rightNeighbor != null)
            map.put("RIGHT", this.rightNeighbor);
        if (this.upperNeighbor != null)
            map.put("UP", this.upperNeighbor);
        if (this.lowerNeighbor != null)
            map.put("DOWN", this.lowerNeighbor);

        return map;
    }

    public void setLowerNeighbor(Node lowerNeighbor, boolean firstTime) {
        this.lowerNeighbor = lowerNeighbor;
        this.neighborMap.put("DOWN", this.lowerNeighbor);

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

    public game.entities.Vector3f getPosition() {
        return this.block.getPosition();
    }

}
