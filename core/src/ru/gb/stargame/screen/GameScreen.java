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
    private int speedShip;

    private Vector2 touchPos;
    private Vector2 moveShip;
    private float distShipAndTouchPos;
    private Vector2 centerShip;
    private int accelerationShip = 3;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        background = new Texture("midgard.png");
        ship = new Texture("pepelaz.png");
        centerShip = new Vector2(ship.getWidth() / 2, ship.getHeight() / 2);
        posShip = new Vector2(background.getWidth() / 2, background.getHeight() / 2);
        touchPos = new Vector2(posShip);
        moveShip = new Vector2(posShip);
        speedShip = 3;

        distShipAndTouchPos = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ship, posShip.x - centerShip.x, posShip.y - centerShip.y);
        batch.end();

        if (distShipAndTouchPos > 0) {
            distShipAndTouchPos -= speedShip;
            posShip.add(moveShip);
        } else {
            distShipAndTouchPos = 0;
        }
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
        moveShip.set(touchPos).sub(posShip);
        distShipAndTouchPos = moveShip.len();
        moveShip.nor().scl(speedShip);

        System.out.println(moveShip);

        return false;
    }

}
