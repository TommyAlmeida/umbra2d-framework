package com.lewk.umbra.graphics;

public class Color {

    public static final Color RED = new Color(255, 0, 0, 1);
    public static final Color BLACK = new Color(0, 0, 0, 1);

    private float r;
    private float g;
    private float b;
    private float alpha;

    private Color(float r, float g, float b, float alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    private Color() {
        this(0, 0, 0, 1);
    }

    public static Color empty() {
        return new Color();
    }

    public static Color of(float r, float g, float b, float alpha) {
        return new Color(r, g, b, alpha);
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
