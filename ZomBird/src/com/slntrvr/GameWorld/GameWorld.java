package com.slntrvr.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.slntrvr.GameObjects.Bird;
import com.slntrvr.GameObjects.ScrollHandler;
import com.slntrvr.Helpers.AssetLoader;



public class GameWorld {
	
	public enum GameState {
	    READY, RUNNING, GAMEOVER, HIGHSCORE
	}
	private GameState currentState;

	
	private Bird bird;
	private ScrollHandler scrollHandler;
	private Rectangle ground;
	private int score,midPointY;

	public GameWorld(int midPointY){
		this.midPointY = midPointY;
		bird = new Bird(33, midPointY - 5, 17, 12);
		scrollHandler = new ScrollHandler(this, midPointY+66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
		currentState = GameState.READY;
	}
	
	public void update(float delta) {

        switch (currentState) {
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
            updateRunning(delta);
            break;
        default:
            break;
        }

    }
	
	public void updateReady(float delta){
		
	}

	public void updateRunning(float delta) {
		
		if (delta > .15f) {
            delta = .15f;
        }
		
		
		bird.update(delta);
		scrollHandler.update(delta);
		
		 if (bird.isAlive() && scrollHandler.collides(bird)) {
		        // Clean up on game over
		        scrollHandler.stop();
		        AssetLoader.dead.play();
		        bird.die();
		  }
		 
		 if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
	            scrollHandler.stop();
	            bird.die();
	            bird.decelerate();
	            currentState = GameState.GAMEOVER;
	            
	            if (score > AssetLoader.getHighScore()) {
	                AssetLoader.setHighScore(score);
	                currentState = GameState.HIGHSCORE;
	            }
	        }
		 
	}
	
	
	public Bird getBird(){
		return bird;
	}

	public ScrollHandler getScroller() {
		// TODO Auto-generated method stub
		return scrollHandler;
	}
	
	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score+=increment;
	}
	
	public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

}
