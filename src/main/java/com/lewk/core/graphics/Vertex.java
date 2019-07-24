package com.lewk.core.graphics;

import com.lewk.core.math.Vector3;

public class Vertex {

    private Vector3 position;

    private Vertex(Vector3 position) {
        this.position = position;
    }

    public static Vertex of(float x, float y, float z) {
        return new Vertex(new Vector3(x, y, z));
    }

    public Vector3 getPosition() {
        return position;
    }
}
