package com.example.android.lasergame;

import android.util.FloatMath;

public class Vector2 {
    public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
    protected float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }
    
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public Vector2 add(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 add(Vector2 other) {
        return add(other.x, other.y);
    }

    public Vector2 sub(float x, float y) {
        return new Vector2(this.x - x, this.y - y);
    }

    public Vector2 sub(Vector2 other) {
        return sub(other.x, other.y);
    }

    public Vector2 mul(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public float len() {
        return (float) Math.sqrt(x * x + y * y);
    }

    // makes it a unit vector
    public Vector2 nor() {
        float len = len(), unitX = this.x, unitY = this.y;
        
        if (len != 0) {
            unitX /= len;
            unitY /= len;
        } 
        
        return new Vector2(unitX, unitY);
    }

    public float angle() {
        float angle = (float) Math.atan2(y, x) * TO_DEGREES;
        if (angle < 0)
            angle += 360;
        return angle;
    }
    
    public float dot( Vector2 v )
    {
            return x * v.x + y * v.y;
    }

    public Vector2 rotate(float angle) {
        float rad = angle * TO_RADIANS;
        float cos = FloatMath.cos(rad);
        float sin = FloatMath.sin(rad);

        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;

        return new Vector2(newX, newY);
    }

    public float dist(Vector2 other) {
        float distX = this.x - other.x;
        float distY = this.y - other.y;
        return FloatMath.sqrt(distX * distX + distY * distY);
    }

    public float dist(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return FloatMath.sqrt(distX * distX + distY * distY);
    }

    public float distSquared(Vector2 other) {
        float distX = this.x - other.x;
        float distY = this.y - other.y;
        return distX * distX + distY * distY;
    }

    public float distSquared(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return distX * distX + distY * distY;
    }
}