package com.slntrvr.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.slntrvr.GameRenderer.GameRenderer;
import com.slntrvr.GameWorld.GameWorld;
import com.slntrvr.Helpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameRenderer renderer;
	private GameWorld world;
	private float runTime=0;
	
	
    
    public GameScreen() {
    	float screenWidth = Gdx.graphics.getWidth();
    	float screenHeight = Gdx.graphics.getHeight();
    	float gameWidth = 136;
    	float gameHeight = screenHeight / (screenWidth/gameWidth);
    	
    	int midPointY = (int)gameHeight/2;

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world,(int)gameHeight,midPointY);
        
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
    	runTime+=delta;
    	renderer.render(runTime);
    	world.update(delta);               
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");     
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");        
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");       
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}