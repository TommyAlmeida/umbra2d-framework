package com.lewk.umbra.core.kernel;

import com.lewk.umbra.core.io.Display;
import com.lewk.umbra.core.io.DisplayConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Game implements IGameLogic {

    private ExecutorService executorService;
    private Display display;

    public Game(DisplayConfig displayConfig) {
        executorService = Executors.newCachedThreadPool();
        display = new Display(displayConfig);
    }

    public void start(){
        GameTask gameTask = new GameTask(display, this);
        executorService.submit(gameTask);
    }
}
