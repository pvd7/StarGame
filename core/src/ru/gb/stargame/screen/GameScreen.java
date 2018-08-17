package ru.gb.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.stargame.base.Base2DScreen;
import ru.gb.stargame.base.MySprite;
import ru.gb.stargame.math.MyRect;

public class GameScreen extends Base2DScreen {

    Texture img;
    MySprite logo;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        logo = new MySprite(new TextureRegion(img));
        logo.setSize(1f, 1f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        logo.draw(batch);
        logo.update(0.5f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public void resize(MyRect worldBounds) {
        super.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
    }

}
