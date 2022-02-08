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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import java.io.IOException;
import java.util.*;

public class GameEngine extends Application implements Runnable {
    //These need to be static because they will get reset otherwise when the game engine is started

    //Map to different things to draw, LinkedHashMap to maintain order of insertion
    private static Map<String, List<Drawable>> layers = new LinkedHashMap<>();

    //Holds loaded textures so things won't load twice
    private static HashMap<String, Image> textures = new HashMap<>();

    //The keys pressed at the moment
    private static HashSet<KeyCode> keysPressed = new HashSet<>();

    //The Box2D world
    private static World world = new World(new Vec2());

    //Needed for drawing
    private GraphicsContext ctx;

    //Window width and height
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

    public Body addBody(BodyDef def) {
        return world.createBody(def);
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(width, height);
        ctx = canvas.getGraphicsContext2D();
        world.setGravity(new Vec2(0, 0));

        //This is the actual game loop
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                long delta = now - lastUpdate;
//                float worldDelta = delta / 1e6f;
                world.step(1f/60f, 2, 6);
                draw();
                lastUpdate = now;
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

    @Override
    public void run() {
        launch();
    }
}