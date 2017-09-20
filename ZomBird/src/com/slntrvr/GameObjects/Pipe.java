package com.slntrvr.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable{

	private Random r;
	private Rectangle skullUp, skullDown, barUp, barDown;
	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	private float groundY;
	private boolean isScored = false;
	
	@Override
    public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));

        // Our skull width is 24. The bar is only 22 pixels wide. So the skull
        // must be shifted by 1 pixel to the left (so that the skull is centered
        // with respect to its bar).
        
        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);

    }
	
	public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
                        .overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }

	public Rectangle getSkullUp() {
		return skullUp;
	}

	public void setSkullUp(Rectangle skullUp) {
		this.skullUp = skullUp;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public void setSkullDown(Rectangle skullDown) {
		this.skullDown = skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public void setBarUp(Rectangle barUp) {
		this.barUp = barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public void setBarDown(Rectangle barDown) {
		this.barDown = barDown;
	}

	public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		skullUp = new Rectangle();
	    skullDown = new Rectangle();
	    barUp = new Rectangle();
	    barDown = new Rectangle();
	    this.groundY = groundY;

	}
	
	@Override
	public void reset(float newX){
		super.reset(newX);
		height=r.nextInt(90)+15;
		isScored=false;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean isScored) {
		this.isScored = isScored;
	}
	
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);
	}

}
