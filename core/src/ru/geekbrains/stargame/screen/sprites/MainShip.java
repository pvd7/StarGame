package ru.geekbrains.stargame.screen.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class MainShip extends Sprite {

    private static final float SIZE = 0.15f;
    private Vector2 speed;
    private Rect worldBounds;

    public MainShip(TextureAtlas atlas) {
        super(new TextureRegion(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 195, 287)));
        speed = new Vector2();
        setHeightProportion(SIZE);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom());
//        System.out.println("worldBounds.getBottom() " + worldBounds.getBottom());
//        System.out.println("mainShip " + this);
    }

    @Override
    public void update(float delta) {
//        if (worldBounds.isOutside(this)) stopMove();
//        else pos.add(speed);
        pos.add(speed);
    }

    public void startMoveLeft() {
        startMove(-1);
        speed.set(-0.005f, 0f);
    }

    public void startMoveRight() {
        startMove(1);
    }

    public void stopMove() {
        speed.setZero();
    }

    public void startMove(int direct) {
        speed.set(0.005f * direct, 0f);
    }
}
