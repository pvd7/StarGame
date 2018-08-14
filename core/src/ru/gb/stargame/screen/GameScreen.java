package ru.gb.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.gb.stargame.base.Base2DScreen;

public class GameScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;
    private Texture ship;

    private Vector2 posShip;
    private Vector2 speedShip;

    private Vector2 touchPos;
    private Vector2 directionMove;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        background = new Texture("midgard.png");
        ship = new Texture("pepelaz.png");
        posShip = new Vector2((background.getWidth() - ship.getWidth()) / 2, (background.getHeight() - ship.getWidth()) / 2);

        speedShip = new Vector2(3f, 5f);
        touchPos = new Vector2();
        directionMove = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ship, posShip.x, posShip.y);
        batch.end();
        posShip.add(speedShip);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
        ship.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touchPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touchPos.x = " + touchPos.x + " touchPos.y = " + touchPos.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        return false;
    }

}
