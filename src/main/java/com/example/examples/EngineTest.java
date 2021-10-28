package com.example.examples;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class EngineTest {
    public static void main(String[] args) {
        GameEngine e = new GameEngine();

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

        new Thread(e).start();
    }
}
