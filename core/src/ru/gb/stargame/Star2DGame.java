package ru.gb.stargame;

import com.badlogic.gdx.Game;

import ru.gb.stargame.screen.GameScreen;

public class Star2DGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

}
