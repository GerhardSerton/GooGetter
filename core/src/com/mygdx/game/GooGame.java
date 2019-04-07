package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GooGame extends Game{

    public  SpriteBatch sBatch;
    public BitmapFont bFont;

    public void create(){
        bFont = new BitmapFont();
        sBatch = new SpriteBatch();
        this.setScreen(new DumbassMenu((this)));

    }

    public void render(){
        super.render();
    }

    public void dispose(){
        bFont.dispose();
        sBatch.dispose();
    }
}
