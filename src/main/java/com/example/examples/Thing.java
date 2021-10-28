package com.example.examples;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

public abstract class Thing implements Drawable {
    private Vector position;
    private String texture;

    public Thing(Vector position, String texture) {
        this.position = position;
        this.texture = texture;
    }

    public Thing(String texture) {
        position = new Vector();
        this.texture = texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void translate(Vector transform) {
        position.x += transform.x;
        position.y += transform.y;
    }

    public abstract void update();

    @Override
    public void draw(GraphicsContext gc, HashMap<String, Image> textures) {
        update();
        if(!textures.containsKey(texture)) {
            textures.put(texture, new Image(texture));
        }
        gc.drawImage(textures.get(texture), position.x, position.y);
    }
}
