package com.example.examples;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

// This is how the engine would actually be used
public class EngineTest {
    public static void main(String[] args) {
        //Initializing the engine
        GameEngine e = new GameEngine();

        //Just a smiley face that moves with arrow keys
        Thing t = new Thing(new Vector(), "smile.jpg") {
            @Override
            public void update() {
                if(e.isPressed(KeyCode.W)) {
                    translate(new Vector(0, -1));
                }
                if(e.isPressed(KeyCode.A)) {
                    translate(new Vector(-1, 0));
                }
                if(e.isPressed(KeyCode.S)) {
                    translate(new Vector(0, 1));
                }
                if(e.isPressed(KeyCode.D)) {
                    translate(new Vector(1, 0));
                }
            }
        };
        e.addLayer("smiley");
        e.getLayer("smiley").add(t);

        // Launches the game
        new Thread(e).start();
    }
}
