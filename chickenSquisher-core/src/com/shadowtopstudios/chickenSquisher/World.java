package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World
{
	protected OrthographicCamera mCamera;
	public static final int CAMERA_WIDTH=80;
	public static final int CAMERA_HEIGHT=60;
	protected GameScene mGameScene;
	
	protected SpriteBatch mShadowBatch;
	protected SpriteBatch mMeteorBatch;
	protected SpriteBatch mChickBatch;
	
	protected AnimalContainer mAnimals;
	
	
	public World(GameScene scene)
	{
		mGameScene = scene;
		mCamera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
		mCamera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
		mCamera.update();
		mShadowBatch = new SpriteBatch();
		mMeteorBatch = new SpriteBatch();
		mChickBatch = new SpriteBatch();
		
		mAnimals = new AnimalContainer(this);
	}
	public void update(float delta)
	{
		//mMeteorContainer.update(delta);
		mAnimals.update(delta);
	}
	public void draw()
	{
		mShadowBatch.begin();
		mShadowBatch.setProjectionMatrix(mCamera.combined);
		//meteor container draw shadows
		mShadowBatch.end();
		
		mChickBatch.begin();
		mChickBatch.setProjectionMatrix(mCamera.combined);
		mAnimals.draw(mChickBatch);
		mChickBatch.end();
		
		mMeteorBatch.begin();
		mMeteorBatch.setProjectionMatrix(mCamera.combined);
		//meteor container drawMeteors
		mMeteorBatch.end();
	}
	public void addTouch(float x,float y,int pointer)
	{
		//add touch to animal container
		mAnimals.addTouch(x,y,pointer);
	}
	public void deleteTouch(int pointer)
	{
		mAnimals.deleteTouch(pointer);
	}
}
