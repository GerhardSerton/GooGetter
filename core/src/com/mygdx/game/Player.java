package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity;

public class Player extends Entity{


    GooGetter.Direction direction;
    public Player(GooGetter game, int x, int y, int speed, Texture texture) {
        super(game, x, y, speed, texture);
    }
    @Override
    public void update(float delta) {

        dx = 0;
        dy = 0;

        // move
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            dy = (int)(speed * delta);
            direction = GooGetter.Direction.U;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            dy = (int)(-speed * delta);
            direction = GooGetter.Direction.D;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            dx = (int)(-speed * delta);
            direction = GooGetter.Direction.L;
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            dx = (int)(speed * delta);
            direction = GooGetter.Direction.R;
        }
    }

    public Tile nearestTile(){
        int cx = x;
        int cy = y;

        cx = cx / 20;
        cy = cy / 20;

        return new Tile(cx, cy, false);


    }
}
