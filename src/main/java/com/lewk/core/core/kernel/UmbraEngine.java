package com.lewk.core.core.kernel;

import com.lewk.core.utils.Constants;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.glfw.GLFW.*;

public class UmbraEngine {

    private static int fps;
    private static float framerate = 200;
    private static float frameTime = 1.0f / framerate;
    private boolean isRunning;

    private IGameLogic gameLogic;

    public UmbraEngine(IGameLogic gameLogic, int width, int height, String title) {
        this.gameLogic = gameLogic;
        createWindow(width, height, title);
    }

    public void createWindow(int width, int height, String title) {
        glfwInit();
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        Window.getInstance().create(width, height, title);

        getDeviceProperties();
    }

    private void init() {
        gameLogic.onInit();
    }

    public void start() {
        if (isRunning){
            return;
        }

        run();
    }

    public void run() {
        this.isRunning = true;

        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;


        init();

        while (isRunning) {
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) Constants.NANOSECOND;
            frameCounter += passedTime;


            while (unprocessedTime > frameTime) {
                unprocessedTime -= frameTime;

                if (Window.getInstance().isCloseRequested())
                    stop();

                update(unprocessedTime);

                if (frameCounter >= Constants.NANOSECOND) {
                    fpsCap(frames);
                    frames = 0;
                    frameCounter = 0;
                }

                frames++;
            }
        }

        cleanUp();
    }

    private void stop() {
        if (!isRunning)
            return;

        isRunning = false;
        gameLogic.onDispose();
    }

    private void update(double deltatime) {
        gameLogic.onUpdate(deltatime);
    }

    private void cleanUp() {
        Window.getInstance().dispose();
        glfwTerminate();
    }

    private void getDeviceProperties() {
        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION) + " bytes");
        System.out.println("Max Geometry Uniform Blocks: " + GL31.GL_MAX_GEOMETRY_UNIFORM_BLOCKS + " bytes");
        System.out.println("Max Geometry Shader Invocations: " + GL40.GL_MAX_GEOMETRY_SHADER_INVOCATIONS + " bytes");
        System.out.println("Max Uniform Buffer Bindings: " + GL31.GL_MAX_UNIFORM_BUFFER_BINDINGS + " bytes");
        System.out.println("Max Uniform Block Size: " + GL31.GL_MAX_UNIFORM_BLOCK_SIZE + " bytes");
        System.out.println("Max SSBO Block Size: " + GL43.GL_MAX_SHADER_STORAGE_BLOCK_SIZE + " bytes");
    }

    public static float getFrameTime() {
        return frameTime;
    }

    public static int getFps() {
        return fps;
    }

    public static void fpsCap(int fps) {
        UmbraEngine.fps = fps;
    }
}
