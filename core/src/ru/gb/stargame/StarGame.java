package ru.gb.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture ship;
    private Texture background;

    Vector2 imgStartPos;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("midgard.png");

        ship = new Texture("pepelaz.png");
        imgStartPos = new Vector2((background.getWidth() - ship.getWidth()) / 2, (background.getHeight() - ship.getWidth()) / 2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ship, imgStartPos.x, imgStartPos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        ship.dispose();
    }

}
