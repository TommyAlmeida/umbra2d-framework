package com.lewk.core.core.kernel;

public interface IGameLogic {

    void onInit();

    void onUpdate(double deltatime);

    void onDispose();
}
