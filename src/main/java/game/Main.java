package game;

import engine.GameEngine;
import engine.Scene;
import game.scenes.MenuScene;

import java.io.File;

public class Main {
 
    public static void main(String[] args) {
        try {
            System.setProperty("org.lwjgl.librarypath", new File("target/natives").getAbsolutePath());
            System.out.println();
            boolean vSync = true;
            Scene scene = new MenuScene();
//            Scene scene = new GameScene();
            GameEngine gameEng = new GameEngine("Pacman 3D", 1280, 720, vSync, scene);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}