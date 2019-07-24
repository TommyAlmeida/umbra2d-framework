package com.lewk.core.core.kernel;

public abstract class Game implements IGameLogic {

    private UmbraEngine engine;

    public Game(int width, int height, String title) {
        engine = new UmbraEngine(this, width, height, title);
    }

    public void start(){
        engine.start();
    }
}
