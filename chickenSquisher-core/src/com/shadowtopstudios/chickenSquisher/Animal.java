package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Animal
{
	protected float mX;
	protected float mY;
	protected float mDx;
	protected float mDy;
	protected float mSpeed;
	protected float mAngle;
	protected float mRadius;
	
	protected float mWidth;
	protected Texture mTexture;
	protected Touch mParent;
	protected AnimalContainer mOthers;
	protected Random rand;
	protected int mId;
	
	
	public void draw(SpriteBatch batch)
	{
		//batch.draw(mTexture, mX, mY, 0, 0, mWidth, mWidth, 1, 1);
		batch.draw(mTexture, mX, mY, 0.f, 0.f, mWidth, mWidth, 1.f, 1.f, mAngle, 0, 0, (int)mWidth, (int)mWidth, false, false);
	}
	public abstract boolean update(float delta);
	
	public void findSlope()
	{
		double radians = ((double)mAngle/180)*Math.PI;
		mDx = (float)(Math.cos(radians)*mSpeed);
		mDy = (float)(Math.sin(radians)*mSpeed);
	}
	public float getAngle(float x,float y)
	{
		float deltaX = x-mX;
		float deltaY = y-mY;
		float angle = (float)(Math.atan2((double)deltaY,(double)deltaX));
		if(mAngle < 0)
		{
			angle = 360 -(-angle);
		}
		return angle;
	}
	public void moveIfFree()
	{
		int id;
		id = mOthers.collisionWithOthers(mX+mDx,mY+mDy,mId);
		if(id == -1)
		{
			mX+=mDx;
			mY+=mDy;
			return;
		}
		id = mOthers.collisionWithOthers(mX+mDx,mY,mId);
		if(id == -1)
		{
			mX+=mDx;
			return;
		}
		id = mOthers.collisionWithOthers(mX,mY+mDy,mId);
		if(id==-1)
		{
			mY+=mDy;
			return;
		}
		return;
	}
	public void addTouch(float x,float y,int pointer)
	{
		if(mParent.mPointer == pointer)
		{
			mParent.mX = x;
			mParent.mY = y;
			return;
		}
		if(distanceFromMe(mParent.mX,mParent.mY) >distanceFromMe(x,y))
		{
			mParent.mX = x;
			mParent.mY = y;
			mParent.mPointer=pointer;
		}
	}
	public float distanceFromMe(float x,float y)
	{
		float xx = x-mX;
		float yy = y-mY;
		xx *=xx;
		yy *=yy;
		return xx+yy;
	}
	public void deleteTouch(int pointer)
	{
		if( pointer == mParent.mPointer)
		{
			getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		}
	}
	public void getRandomParent(int width,int height)
	{
		mParent.mX = (float) rand.nextInt(width);
		mParent.mY = (float) rand.nextInt(height);
		mParent.mPointer = -1;
	}
	public boolean distanceFromParent(int dist)
	{
		boolean x = false;
		boolean y = false;
		if(mParent.mX-mX>0)
		{
			if(mParent.mX-mX <dist)
			{
				x = true;
			}
		}
		else
		{
			if(mX - mParent.mX <dist)
			{
				x = true;
			}
		}
		if(mParent.mY-mY >0)
		{
			if(mParent.mY-mY < dist)
			{
				y=true;
			}
		}
		else
		{
			if(mY-mParent.mY <dist)
			{
				y = true;
			}
		}
		return x && y;
	}
	
}
