package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteor {
	
	public int id;
	public float shadowSpeed;
	public float meteorSpeed;
	public float sizeX;
	public float sizeY;
	public float spawnX;
	public float spawnY;
	private Texture mIMG;
	private float meteorSize;
	private float collisionSize;
	
	
	public Meteor(int id, float sspeed, float mspeed, float startsize, float spawnx, float spawny, float meteorsize, float collisionsize){
		shadowSpeed = sspeed;
		meteorSpeed = mspeed;
		sizeX = startsize;
		sizeY = startsize;
		spawnX = spawnx;
		spawnY = spawny;
		meteorSize = meteorsize;
		collisionSize = collisionsize;
	}
	
	public void update(){
		
	}
	
	public void draw(SpriteBatch mBatch){
		
	}
	
}
