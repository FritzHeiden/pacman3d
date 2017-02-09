package game;

import engine.GameEngine;
import engine.Scene;
import game.scenes.GameScene;

public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            Scene scene = new GameScene();
            GameEngine gameEng = new GameEngine("Pacman 3D", 1280, 720, vSync, scene);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}