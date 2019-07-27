package com.lewk.umbra.core.io;

import com.lewk.umbra.graphics.Color;
import lombok.Builder;
import lombok.Data;

@Builder
public class DisplayConfig {

    private int width;
    private int height;

    private String title;
    private boolean resizable;
    private Color background;

    public DisplayConfig(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        background = Color.BLACK;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }
}
