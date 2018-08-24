package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.screen.sprites.Background;
import ru.geekbrains.stargame.screen.sprites.MainShip;
import ru.geekbrains.stargame.screen.sprites.Star;


public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 56;

    private Background background;
    private Texture bgTexture;
    private TextureAtlas mainAtlas;
    private MainShip mainShip;
    private Star[] star;
    private boolean pressLeftKey = false;
    private boolean pressRightKey = false;
    private Rect worldBounds;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bgTexture));

        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(mainAtlas);
        }
        mainShip = new MainShip(mainAtlas);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        batch.end();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        mainShip.update(delta);
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        System.out.println("worldBounds = " + worldBounds);

        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
//        System.out.println("worldBounds.getBottom() " + worldBounds.getBottom());
//        System.out.println("mainShip " + mainShip);
    }

    @Override
    public void dispose() {
        super.dispose();
        bgTexture.dispose();
        mainAtlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            pressLeftKey = true;
            mainShip.startMoveLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            pressRightKey = true;
            mainShip.startMoveRight();
        }

        System.out.println("background = " + background);
        System.out.println("worldBounds " + worldBounds);
        System.out.println("mainShip " + mainShip);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) pressLeftKey = false;
        if (keycode == Input.Keys.RIGHT) pressRightKey = false;

        if (!pressLeftKey && !pressRightKey) mainShip.stopMove();

        return super.keyUp(keycode);
    }
}
