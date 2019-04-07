package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenu implements Screen{

    GooGame supergame;
    OrthographicCamera  gameCamera;

    Stage stage;
    TextButton textButton;
    TextButton.TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas textureAtlas;


    public MainMenu(GooGame game)
    {
        supergame = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


    }

    @Override
    public void render(float f)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        //gameCamera.update();
        supergame.sBatch.begin();

        supergame.sBatch.end();

    }

    public void show(){}
    public void resize(int x, int y){}
    public void pause(){}
    public void resume(){}
    public void hide(){}
    public void dispose(){}

}
