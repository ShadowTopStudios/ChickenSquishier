package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteor {
	
	protected int mId;
	protected float shadowSpeed;
	protected float savedShadowSpeed;
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
	
	protected int timerMax = 1;
	protected int timer = timerMax;
	
	public Meteor(int id, float sspeed, float mspeed, float startsize, float spawnx, float spawny, float meteorsize, float collisionsize){
		int randIMG = RandomNumber.random(1,4);
		mId = id;
		shadowSpeed = sspeed;
		savedShadowSpeed = sspeed;
		sizeX = startsize;
		sizeY = startsize;
		savedSizeX = startsize;
		savedSizeY = startsize;
		spawnX = spawnx;
		spawnY = spawny;
		meteorSize = meteorsize;
		collisionSize = collisionsize;
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
			shadowSpeed = 0;
			timer--;
		}
		if(timer <= 0){
			keepUpdating = false;
		}
		return keepUpdating;
	}
	
	public void resetMeteor(){
		sizeX = savedSizeX;
		sizeY = savedSizeY;
		timer = timerMax;
		shadowSpeed = savedShadowSpeed;
		switchToMeteor = false;
		keepUpdating = true;
	}
	public void draw(SpriteBatch mBatch){
		mBatch.draw(mIMG, spawnX - (sizeX / 2), spawnY - (sizeY / 2), sizeX + 5, sizeY + 5);
	}
	
	public void drawShadow(SpriteBatch sBatch){
		sBatch.draw(sIMG, spawnX - (sizeX / 2), spawnY - (sizeY / 2), sizeX, sizeY);
	}
}