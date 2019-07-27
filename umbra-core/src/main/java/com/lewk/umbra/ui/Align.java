package com.lewk.umbra.ui;

import com.lewk.umbra.math.Vector2;
import javafx.util.Pair;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Align {

    public static Pair<Integer, Integer> center(int width, int height){
        Vector2 position = new Vector2(0, 0);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        int midpointX = (int) position.getX() + (videoMode.width() - width) / 2;
        int midpointY = (int) position.getY() + (videoMode.height() - height) / 2;

        return new Pair<>(midpointX, midpointY);
    }
}
