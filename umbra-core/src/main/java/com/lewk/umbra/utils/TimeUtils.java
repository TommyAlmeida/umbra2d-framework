package com.lewk.umbra.utils;

public class TimeUtils {

    public static long lastTime = System.nanoTime();
    public static double delta = 0.0;
    public static double nanoSeconds = 1000000000.0 / 60.0;
    public static long timer = System.currentTimeMillis();
    public static int updates = 0;
    public static int frames = 0;
    public static double elapsedTime = 0;
    public static long lastFrame = getTime();

    public static long getTime() {
        return System.nanoTime();
    }

    public static int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }
}
