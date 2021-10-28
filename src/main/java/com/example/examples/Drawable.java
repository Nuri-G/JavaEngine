package com.example.examples;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

public interface Drawable {
    void draw(GraphicsContext gc, HashMap<String, Image> textures);
}
