package com.example.examples;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GameEngine extends Application implements Runnable {
    //These need to be static because they will get reset otherwise when the game engine is started
    private static Map<String, List<Drawable>> layers = new HashMap<>();
    private static HashMap<String, Image> textures = new HashMap<>();
    private static HashSet<KeyCode> keysPressed = new HashSet<>();

    private GraphicsContext ctx;
    private int width;
    private int height;

    public GameEngine() {
        width = 800;
        height = 600;
    }

    public boolean isPressed(KeyCode key) {
        return keysPressed.contains(key);
    }

    public void addLayer(String name) {
        layers.put(name, new ArrayList<>());
    }

    public void removeLayer(String name) {
        layers.remove(name);
    }

    public List<Drawable> getLayer(String layer) {
        return layers.get(layer);
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);
        ctx = canvas.getGraphicsContext2D();

        //This is the actual game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                draw();
            }
        };

        //This is basically making a window
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));

        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        stage.setScene(scene);
        stage.setTitle("");
        stage.show();
        timer.start();
    }

    private void draw() {
        ctx.clearRect(0, 0, width, height);
        for (String layerName : layers.keySet()) {
            List<Drawable> layer = layers.get(layerName);
            for (Drawable d : layer) {
                d.draw(ctx, textures);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void run() {
        launch();
    }
}