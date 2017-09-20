package com.slntrvr.ZomBird;

import com.badlogic.gdx.Game;
import com.slntrvr.Helpers.AssetLoader;
import com.slntrvr.Screens.GameScreen;

public class ZBGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
}
