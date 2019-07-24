package com.lewk.core.core.kernel;

import com.lewk.core.core.io.Display;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;

public class GameTask implements Runnable {

    private Display display;
    private IGameLogic gameLogic;

    public GameTask(Display display, IGameLogic gameLogic) {
        this.display = display;
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        gameLoop();
    }

    private void gameLoop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        display.fill();

        gameLogic.onCreate();

        while (!display.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            display.swapColorBuffers();

            gameLogic.onRender(System.nanoTime());
            glfwPollEvents();
        }

        //TODO: It must dispose only when the thread has exited, remove this in the future
        gameLogic.onDispose();
    }
}
