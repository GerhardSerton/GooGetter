package com.mygdx.game;

public class Level {

    int[][] design;
    int startingX;
    int startingY;

    public Level (int[][] design, int x, int y)
    {
        this.design = design;
        this.startingX = x;
        this.startingY = y;
    }
}
