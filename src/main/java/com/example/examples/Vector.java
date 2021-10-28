package com.example.examples;

public class Vector {
    public float x;
    public float y;

    public Vector() {
        x = 0f;
        y = 0f;
    }
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float magnitude() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Vector scalarMultiply(float scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector norm() {
        return scalarMultiply(1 / magnitude());
    }
}
