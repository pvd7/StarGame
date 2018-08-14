package ru.gb.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.gb.stargame.Star2DGame;
import ru.gb.stargame.StarGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "StarGame";
		config.height = 450;
		config.width = 800;
		new LwjglApplication(new Star2DGame(), config);
	}

}
