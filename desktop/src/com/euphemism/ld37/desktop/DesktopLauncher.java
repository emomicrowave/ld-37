package com.euphemism.ld37.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.euphemism.ld37.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Accelerated Dating";
        config.width = 1200;
        config.height = 680;
        config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
