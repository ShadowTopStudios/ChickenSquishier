package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Particle
{
	protected Texture mTexture;
	protected Sprite mSprite;
	protected float mX;
	protected float mY;
	protected float mAngle;
	protected float mAlpha;
	protected float mDx;
	protected float mDy;
	protected float mWidth;
	protected float mHeight;
	protected float mSpeed;
	protected float mOffsetX;
	protected float mOffsetY;
	protected boolean mAlive;
	public int mSkitterMax;
	public int mSkitter;
	protected Random rand;
	protected int mTimer;
	protected int mTimerMax;
	protected float mSize;
	protected float mSpeedMax=-1;
	public abstract boolean update(float delta);
	
	public void findSlope()
	{
		double radians = ((double)mAngle/180)*Math.PI;
		mDx = (float)(Math.cos(radians)*mSpeed);
		mDy = (float)(Math.sin(radians)*mSpeed);
	}
	public void reset(float x,float y)
	{
		mX = x+mOffsetX;
		mY = y+mOffsetY;
		mAlive = true;
		mAlpha = 1.f;
		mTimer = mTimerMax;
		mSize = 1.f;
		if(mSpeedMax !=-1)
		{
			mSpeed = mSpeedMax;
		}
	}
	public void draw(SpriteBatch batch)
	{
		mSprite.draw(batch);
	}
}
