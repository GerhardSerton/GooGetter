package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GooGetter.Direction;

public class Entity {

    public GooGetter game;
    public int x;
    public int y;
    public int dx;
    public int dy;
    public int speed;
    public Texture texture;

    public Entity(GooGetter game, int x, int y, int speed, Texture texture){
        this.game = game;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
    }

    public void update(float delta)
    {};

    public void move(int newX, int newY){
        x = newX;
        y = newY;

    }

    public void tileCollision(int tile, int tileX, int tileY, float newX, float newY, Direction direction) {
        System.out.println("tile collision at: " + tileX + " " + tileY);

        if(direction == Direction.U) {
            y = tileY * 20 + 20;
        }
        else if(direction == Direction.D) {
            y = tileY * 20 - 20;
        }
        else if(direction == Direction.L) {
            x = tileX * 20 + 20;
        }
        else if(direction == Direction.D) {
            x = tileX * 20 - 20;
        }
    }

    public boolean entityCollision(){
        return false;
    }

}
