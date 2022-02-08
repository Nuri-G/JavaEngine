package com.example.examples;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

// This is how the engine would actually be used
public class EngineTest {
    public static void main(String[] args) {
        //Initializing the engine
        GameEngine e = new GameEngine();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, 2, new Vec2(), 0);
        BodyDef b = new BodyDef();
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.3f;
        b.type = BodyType.DYNAMIC;
        Body body = e.addBody(b);
        body.createFixture(fd);
        body.setTransform(new Vec2(10, 10), 0);


        shape.setAsBox(2, 2, new Vec2(), 0);
        fd.shape = shape;
        Body body2 = e.addBody(b);
        body2.setTransform(new Vec2(15, 15), 0);
        body2.createFixture(fd);


        //Just a smiley face that moves with arrow keys
        Thing t = new Thing(body, "smile.jpg", 20, 20) {

            @Override
            public void update() {
                Vec2 rotated = new Vec2((float)Math.sin(body.getAngle() / 180 * Math.PI), -(float)Math.cos(body.getAngle() / 180 * Math.PI));
                rotated = rotated.mul(20);
//                System.out.println(rotated + " : " + body.getAngle());
                if(e.isPressed(KeyCode.W)) {
                    body.applyForce(rotated, new Vec2());
//                    System.out.println("Moving world " + body.getPosition());

//                    body.setTransform(new Vec2(50, 50), 46);
                }
                if(e.isPressed(KeyCode.A)) {
//                    body.applyForce(new Vec2(-20, 0), new Vec2());
                    body.setAngularVelocity(-100);
                }
                if(e.isPressed(KeyCode.S)) {
                    body.applyForce(rotated.mul(-1), new Vec2());
//                    System.out.println("Here " + rotated);
                }
                if(e.isPressed(KeyCode.D)) {
//                    body.applyForce(new Vec2(20, 0), new Vec2());}
                      body.setAngularVelocity(100);
                } else if(!e.isPressed(KeyCode.A)){
                    body.setAngularVelocity(0);
                }
            }
        };

        Thing two = new Thing(body2, "smile.jpg", 20, 20) {
            @Override
            public void update() {
                return;
            }
        };
        e.addLayer("smiley");
        e.getLayer("smiley").add(t);
        e.getLayer("smiley").add(two);

        // Launches the game
        new Thread(e).start();
    }
}
