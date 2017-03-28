package com.paulclegg.flappygran.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.paulclegg.flappygran.FlappyGran;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyGran.WIDTH;
		config.height = FlappyGran.HEIGHT;
		config.title = FlappyGran.TITLE;
		new LwjglApplication(new FlappyGran(), config);
	}
}
