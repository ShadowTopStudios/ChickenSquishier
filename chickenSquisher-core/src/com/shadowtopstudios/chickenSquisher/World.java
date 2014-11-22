package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	
	protected Texture mBackground;
	
	protected AnimalContainer mAnimals;
	
	protected MeteorsContainer mMeteorsContainer;
	
	public World(GameScene scene)
	{
		mGameScene = scene;
		mCamera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
		mCamera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
		mCamera.update();
		mShadowBatch = new SpriteBatch();
		mMeteorBatch = new SpriteBatch();
		mChickBatch = new SpriteBatch();
		mMeteorsContainer = new MeteorsContainer(this);
		
		mAnimals = new AnimalContainer(this);
		mBackground = new Texture(Gdx.files.internal("Background_desert.png"));
	}
	public void update(float delta)
	{
		mMeteorsContainer.update(delta);
		mAnimals.update(delta);
	}
	public void checkChicks(float x,float y,float radius)
	{
		Gdx.app.log("check chicks:", "spawnX: " + x + " spawnY: " + y + " radius: " + radius);
		mAnimals.killChicks(x,y,radius);
	}
	public void draw()
	{
		mShadowBatch.begin();
		mShadowBatch.setProjectionMatrix(mCamera.combined);
		mShadowBatch.draw(mBackground,0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
		mMeteorsContainer.drawShadow(mShadowBatch);
		//meteor container draw shadows
		mShadowBatch.end();
		
		mChickBatch.begin();
		mChickBatch.setProjectionMatrix(mCamera.combined);
		mAnimals.draw(mChickBatch);
		mChickBatch.end();
		
		mMeteorBatch.begin();
		mMeteorBatch.setProjectionMatrix(mCamera.combined);
		mMeteorsContainer.draw(mMeteorBatch);
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
