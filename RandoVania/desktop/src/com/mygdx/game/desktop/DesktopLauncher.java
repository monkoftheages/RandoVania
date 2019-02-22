package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TestingGame game = new TestingGame();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		LwjglApplication app = new LwjglApplication(game, config);
		game.setScreen(new TestingScreenAdapter(game));

	}
}
