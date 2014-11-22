package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestWorld
{
	protected OrthographicCamera mCamera;
	public static final int CAMERA_WIDTH=90;
	public static final int CAMERA_HEIGHT=60;
	protected TestGameScene mGameScene;
	
	protected SpriteBatch mShadowBatch;
	protected SpriteBatch mMeteorBatch;
	protected SpriteBatch mChickBatch;
	
	protected MeteorsContainer mMeteorsContainer;
	
	
	public TestWorld(TestGameScene scene)
	{
		mGameScene = scene;
		mCamera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
		mCamera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
		mCamera.update();
		mShadowBatch = new SpriteBatch();
		mMeteorBatch = new SpriteBatch();
		mChickBatch = new SpriteBatch();
		//mMeteorsContainer = new MeteorsContainer(this);
	}
	public void update(float delta)
	{
		mMeteorsContainer.update(delta);
		//mChickContainer.update(delta);
	}
	
	public void checkChicks(float x, float y, float radius){
		Gdx.app.log("check chicks:", "spawnX: " + x + " spawnY: " + y + " radius: " + radius);
	}
	
	public void draw()
	{
		
		mShadowBatch.begin();
		mShadowBatch.setProjectionMatrix(mCamera.combined);
		mMeteorsContainer.drawShadow(mShadowBatch);
		//meteor container draw shadows
		mShadowBatch.end();
		
		mChickBatch.begin();
		mChickBatch.setProjectionMatrix(mCamera.combined);
		//chick container draw
		mChickBatch.end();
		
		mMeteorBatch.begin();
		mMeteorBatch.setProjectionMatrix(mCamera.combined);
		//meteor container drawMeteors
		mMeteorsContainer.draw(mMeteorBatch);
		mMeteorBatch.end();
		
		
	}
	
}
