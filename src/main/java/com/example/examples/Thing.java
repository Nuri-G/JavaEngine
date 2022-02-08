package com.example.examples;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import org.jbox2d.dynamics.Body;

import java.util.HashMap;

//A basic "thing" that will be in the game
public abstract class Thing implements Drawable {
    public Body body;
    private float width;
    private float height;
    private String texture;

    public Thing(Body body, String texture, float width, float height) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.body = body;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract void update();

    @Override
    public void draw(GraphicsContext gc, HashMap<String, Image> textures) {
        update();
        if(!textures.containsKey(texture)) {
            textures.put(texture, new Image(texture));
        }
        gc.save();
        Rotate r = new Rotate(body.getAngle(), body.getPosition().x * 10 + width, body.getPosition().y * 10 + height);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(textures.get(texture), body.getPosition().x * 10, body.getPosition().y * 10, width*2, height*2);
        gc.restore();
    }
}
