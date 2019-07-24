package com.lewk.core.core.io;

import com.lewk.core.utils.Triple;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display {

    private long id;
    private DisplayConfig config;

    public Display(DisplayConfig config){
        this.config = config;
        create();
    }

    private void create(){
        configureGLFW();

        if(config == null){
            throw new RuntimeException("WindowConfig cannot be null");
        }

        id = glfwCreateWindow(config.getWidth(), config.getHeight(), config.getTitle(), NULL, NULL);

        if (id == NULL){
            throw new RuntimeException("Failed to create the GLFW window");
        }

        try ( MemoryStack stack = stackPush() ) {
            //Pointer ints
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(id, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    id,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(id);

        // Disable v-sync
        glfwSwapInterval(0);

        // Make the window visible
        glfwShowWindow(id);
    }

    /**
     * <p>Fill the display with the background color defined on the {@link DisplayConfig}</p>
     */
    public void fill(){
        if(config == null){
            throw new RuntimeException("WindowConfig cannot be null");
        }

        if(config.getBackground() == null){
            throw new RuntimeException("Config background cannot be null");
        }

        Triple<Float, Float, Float> backgroundTriple = Triple.of(
                config.getBackground().getR(),
                config.getBackground().getG(),
                config.getBackground().getB()
        );

        glClearColor(backgroundTriple.getLeft(), backgroundTriple.getMiddle(), backgroundTriple.getRight(), 1.0f);
    }

    public void swapColorBuffers(){
        glfwSwapBuffers(id);
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(id);
    }

    private void configureGLFW() {
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation

        if(config.isResizable()){
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        }
    }
}
