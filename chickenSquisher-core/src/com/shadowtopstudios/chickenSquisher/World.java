package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
	protected SpriteBatch mFeatherBatch;
	protected SpriteBatch mRockBatch;
	
	protected Texture mBackground;
	
	protected AnimalContainer mAnimals;
	protected ParticleContainer mFeathers;
	protected ParticleContainer mRocks;
	protected MeteorsContainer mMeteorsContainer;
	public enum ParticleType
	{
		feather,
		rock,
		fluff,
		shell,
	}
	public enum AnimalType
	{
		chick,
		turtle,
		bunny,
	}
	public enum WorldType
	{
		desert,
		grass,
		ice,
	}
	public World(GameScene scene,AnimalType type,WorldType level)
	{
		mGameScene = scene;
		mCamera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);
		mCamera.position.set(CAMERA_WIDTH/2,CAMERA_HEIGHT/2,0);
		mCamera.update();
		mShadowBatch = new SpriteBatch();
		mMeteorBatch = new SpriteBatch();
		mChickBatch = new SpriteBatch();
		mFeatherBatch =new SpriteBatch();
		mRockBatch = new SpriteBatch();
		mMeteorsContainer = new MeteorsContainer(this);
		mRocks = new ParticleContainer(this,ParticleType.rock);
		switch(type)
		{
		case chick:
			mAnimals = new AnimalContainer(this,type);
			mFeathers = new ParticleContainer(this,ParticleType.feather);
			break;
		case turtle:
			mAnimals = new AnimalContainer(this,type);
			mFeathers = new ParticleContainer(this,ParticleType.shell);
			break;
		case bunny:
			System.out.println("foundit");
			mAnimals = new AnimalContainer(this,type);
			mFeathers = new ParticleContainer(this,ParticleType.fluff);
			break;
		default:
			mAnimals = new AnimalContainer(this,type);
			mFeathers = new ParticleContainer(this,ParticleType.feather);
			break;
		}
		switch(level)
		{
		case grass:
			mBackground = new Texture(Gdx.files.internal("Background_grass.png"));
			break;
		case desert:
			mBackground = new Texture(Gdx.files.internal("Background_desert.png"));
			break;
		case ice:
			mBackground = new Texture(Gdx.files.internal("Background_snow.png"));
			break;
		default:
			mBackground = new Texture(Gdx.files.internal("Background_grass.png"));
			break;
		}
	}
	public void update(float delta)
	{
		mMeteorsContainer.update(delta);
		mAnimals.update(delta);
		mFeathers.update(delta);
		mRocks.update(delta);
	}
	public void checkChicks(float x,float y,float radius)
	{
		//Gdx.app.log("check chicks:", "spawnX: " + x + " spawnY: " + y + " radius: " + radius);
		for(int i=0;i<4;i++)
		{
			addParticle(x,y,ParticleType.rock);
		}
		mAnimals.killChicks(x,y,radius);
	}
	public void draw()
	{
		mShadowBatch.begin();
		mShadowBatch.setProjectionMatrix(mCamera.combined);
		
		//grab the color and set alpha to 1 for no transparency
		Color c = mShadowBatch.getColor();
		mShadowBatch.setColor(c.r, c.g, c.b, 1f);
		mShadowBatch.draw(mBackground,0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
		c = mShadowBatch.getColor();
		mShadowBatch.setColor(c.r, c.g, c.b, .3f);//set alpha to 0.3
		mMeteorsContainer.drawShadow(mShadowBatch);
		//meteor container draw shadows
		mShadowBatch.end();
		
		mChickBatch.begin();
		mChickBatch.setProjectionMatrix(mCamera.combined);
		mAnimals.draw(mChickBatch);
		mChickBatch.end();
		
		mFeatherBatch.begin();
		mFeatherBatch.setProjectionMatrix(mCamera.combined);
		mFeathers.draw(mFeatherBatch);
		mFeatherBatch.end();
		
		mRockBatch.begin();
		mRockBatch.setProjectionMatrix(mCamera.combined);
		mRocks.draw(mRockBatch);
		mRockBatch.end();
		
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
	public void addParticle(float x,float y,ParticleType type)
	{
		switch(type)
		{
		case feather:
			mFeathers.addParticle(x, y);
			break;
		case rock:
			mRocks.addParticle(x, y);
			break;
		default:
			break;
		}
	}
}
