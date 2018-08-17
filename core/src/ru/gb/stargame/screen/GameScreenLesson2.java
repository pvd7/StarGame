package ru.gb.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.gb.stargame.base.Base2DScreen;

public class GameScreenLesson2 extends Base2DScreen {

    private SpriteBatch batch;
    private Texture background;
    private Texture ship;

    // текущие координаты корабля
    private Vector2 posShip;
    // скорость корабля
    private float speedShip;
    // конечная точка
    private Vector2 posTouch;
    // направление движения
    private Vector2 directShip;
    // расстояние между кораблем и конечной точкой
    private float distShipAndTouchPos;
    // половина пути изначального расстояния между кораблем и конечной точкой
    private float distShipAndTouchPosDiv2;
    // центр корабля
    private Vector2 centerShip;
    // ускорение корабля
    private float accelerationShip;

    public GameScreenLesson2(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        batch.getProjectionMatrix().idt();
        background = new Texture("midgard.png");
        ship = new Texture("pepelaz.png");
        centerShip = new Vector2(ship.getWidth() / 2, ship.getHeight() / 2);
        posShip = new Vector2(background.getWidth() / 2, background.getHeight() / 2);
        posTouch = new Vector2(posShip);
        directShip = new Vector2(posShip);
        accelerationShip = 0.5f;
        distShipAndTouchPos = 0;

//        MySprite
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(background, -1f, -1f, 2f, 2f);
        batch.draw(ship, posShip.x - centerShip.x, posShip.y - centerShip.y);
        batch.end();

        if (distShipAndTouchPos != 0) {
            if (distShipAndTouchPos > speedShip) {
                // укоряемся первую половину пути, вторую половину пути оттормаживаемся
                speedShip = Math.abs(speedShip + ((distShipAndTouchPos > distShipAndTouchPosDiv2) ? accelerationShip : -accelerationShip));
//                if (distShipAndTouchPos - Math.abs(speedShip) <= 0) speedShip = distShipAndTouchPos;
                posShip.add(directShip.x * speedShip, directShip.y * speedShip);
                distShipAndTouchPos -= Math.abs(speedShip);

//            System.out.println(speedShip);
//            System.out.println(posShip);
            } else {
                posShip.set(posTouch);
                distShipAndTouchPos = 0;
                speedShip = 0;
            }
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
        posTouch.set(screenX, Gdx.graphics.getHeight() - screenY);
        directShip.set(posTouch).sub(posShip);
        distShipAndTouchPos = directShip.len();
        distShipAndTouchPosDiv2 = distShipAndTouchPos / 2;
        directShip.nor();

        System.out.println(directShip);

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);

        return false;
    }
}
