package ru.gb.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.gb.stargame.Star2DGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "StarGame";
//		config.height = 1960;
//		config.width = 1080;
		new LwjglApplication(new Star2DGame(), config);
	}

}
