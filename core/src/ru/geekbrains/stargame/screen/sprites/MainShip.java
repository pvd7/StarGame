package ru.geekbrains.stargame.screen.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.screen.pool.BulletPool;

public class MainShip extends Ship {

    private static final float SIZE = 0.15f;
    private static final float SPEED = 0.3f;

    private boolean pressedKeyLeft;
    private boolean pressedKeyRight;

    private int touchedLeft;
    private int touchedRight;

    Sound sound;
    private int touched;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 0);
        speed = new Vector2();
        setHeightProportion(SIZE);
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, 0.5f);
        this.bulletDamage = 1;
        this.bulletPool = bulletPool;
        sound = Gdx.audio.newSound(Gdx.files.internal("music/Sound_16482.mp3"));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(speed, delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stopMove();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stopMove();
            ;
        }
    }

    public void startMoveLeft() {
        startMove(-1);
    }

    public void startMoveRight() {
        startMove(1);
    }

    public void stopMove() {
        speed.setZero();
    }

    public void startMove(int direct) {
        speed.set(SPEED * direct, 0f);
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedKeyLeft = true;
                startMoveLeft();
                break;
            case Input.Keys.W:
            case Input.Keys.RIGHT:
                pressedKeyRight = true;
                startMoveRight();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedKeyLeft = false;
                if (pressedKeyRight) startMoveRight();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedKeyRight = false;
                if (pressedKeyLeft) startMoveLeft();
                break;
            case Input.Keys.UP:
            case Input.Keys.SPACE:
                sound.play();
                shoot();
                break;

        }
        if (!pressedKeyLeft && !pressedKeyRight) stopMove();
    }

    public void touchDown(Vector2 touch, int pointer) {
        touched++;
        if (touch.x < worldBounds.pos.x) {
            touchedLeft++;
            startMoveLeft();
        }
        else {
            touchedRight++;
            startMoveRight();
        }
    }

    public void touchUp(Vector2 touch, int pointer) {
        touched--;
        if (touched == 0) {
            stopMove();
        }
    }

    public void touchDragged(Vector2 touch, int pointer) {

    }

}
