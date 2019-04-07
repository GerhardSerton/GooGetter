package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DumbassLevelSelect implements Screen {

    GooGame game;
    Texture img;

    public DumbassLevelSelect(GooGame game){
        this.game = game;
        img = new Texture("levelSelect.png");


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.sBatch.begin();
        game.sBatch.draw(img, 0, 0);
        game.sBatch.end();
        try {
            BufferedWriter saver = new BufferedWriter(new FileWriter("SaveFile.txt"));
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_0))
            {
                saver.write("0");
                saver.close();
                game.setScreen(new GooGetter(true, this.game));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            {
                saver.write("1");
                saver.close();
                game.setScreen(new GooGetter(true, this.game));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            {
                saver.write("2");
                saver.close();
                game.setScreen(new GooGetter(true, this.game));
            }
        }
        catch (IOException e)
        {
            System.out.println("ERROR: FILE NOT FOUND");
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
