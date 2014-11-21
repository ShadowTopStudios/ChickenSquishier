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
	
	public World(GameScene scene)
	{
		mGameScene = scene;
		mCamera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
		mCamera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
		mCamera.update();
		mShadowBatch = new SpriteBatch();
		mMeteorBatch = new SpriteBatch();
		mChickBatch = new SpriteBatch();
	}
	public void update(float delta)
	{
		//mMeteorContainer.update(delta);
		//mChickContainer.update(delta);
	}
	public void draw()
	{
		mShadowBatch.begin();
		mShadowBatch.setProjectionMatrix(mCamera.combined);
		//meteor container draw shadows
		mShadowBatch.end();
		
		mChickBatch.begin();
		mChickBatch.setProjectionMatrix(mCamera.combined);
		//chick container draw
		mChickBatch.end();
		
		mMeteorBatch.begin();
		mMeteorBatch.setProjectionMatrix(mCamera.combined);
		//meteor container drawMeteors
		mMeteorBatch.end();
	}
	
}
