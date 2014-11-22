package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;

public class Meteor {
	static public RandomXS128 random = new RandomXS128();
	
	protected int mId;
	protected float shadowSpeed;
	protected float meteorSpeed;
	protected float sizeX;
	protected float sizeY;
	protected float savedSizeX;
	protected float savedSizeY;
	protected float spawnX;
	protected float spawnY;
	protected Texture mIMG;
	protected Texture sIMG;
	protected float meteorSize;
	protected float collisionSize;
	protected boolean switchToMeteor;
	protected boolean keepUpdating=false;
	
	/** Returns a random number between start and end. */
	static public int random (int start, int end) {
		return start + random.nextInt(end - start + 1);
	}
	
	public Meteor(int id, float sspeed, float mspeed, float startsize, float spawnx, float spawny, float meteorsize, float collisionsize){
		int randIMG = random(1,4);
		mId = id;
		shadowSpeed = sspeed;
		sizeX = startsize;
		sizeY = startsize;
		savedSizeX = startsize;
		savedSizeY = startsize;
		spawnX = spawnx;
		spawnY = spawny;
		meteorSize = 40;
		collisionSize = 25;
		switchToMeteor = false;
		sIMG = new Texture("shadow" + randIMG + ".png");
		mIMG = new Texture("meteor" + randIMG + ".png");
	}
	
	public boolean update(float delta){
		sizeX-= shadowSpeed;
		sizeY-= shadowSpeed;
		if(sizeX < meteorSize){
			switchToMeteor = true;	
		}
		if(sizeX < collisionSize){
			//COLLIDED and reset meteor
			keepUpdating = false;
		}
		
		return keepUpdating;
	}
	
	public void resetMeteor(){
		sizeX = savedSizeX;
		sizeY = savedSizeY;
		switchToMeteor = false;
		keepUpdating = true;
	}
	public void draw(SpriteBatch mBatch){
		mBatch.draw(mIMG, spawnX - (sizeX / 2), spawnY - (sizeY / 2), sizeX + 8, sizeY + 8);
	}
	
	public void drawShadow(SpriteBatch sBatch){
		sBatch.draw(sIMG, spawnX - (sizeX / 2), spawnY - (sizeY / 2), sizeX, sizeY);
	}
}