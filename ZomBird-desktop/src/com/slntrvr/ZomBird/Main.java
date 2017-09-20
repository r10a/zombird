	package com.slntrvr.ZomBird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ZomBird";
		cfg.useGL20 = false;
		cfg.width = 272;
		cfg.height = 408;
		
		new LwjglApplication(new ZBGame(), cfg);
	}
}
