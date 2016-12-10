package com.euphemism.ld37.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.euphemism.ld37.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Accelerated Dating";
        config.width = 800;
        config.height = 480;
		new LwjglApplication(new MainGame(), config);
	}
}
