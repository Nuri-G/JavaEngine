package com.example.examples;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

//Any item that will be drawn should implement Drawable
public interface Drawable {
    void draw(GraphicsContext gc, HashMap<String, Image> textures);
}
