package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
	protected Sprite mTexture;
	protected Touch mParent;
	protected AnimalContainer mOthers;
	protected Random rand;
	protected int mId;
	protected boolean mAlive=true;
	protected int mStrength;
	public boolean mMoving=true;
	public static final int smallRad = 4;
	public static final int bigRad = 10;
	public int mMoveRad = smallRad;
	public int mTimer;
	public int mSkitterMax;
	public int mSkitter;
	
	
	public void draw(SpriteBatch batch)
	{
		//batch.draw(mTexture, mX, mY, 0, 0, mWidth, mWidth, 1, 1);
		//batch.draw(mTexture, mX, mY, mX, mY, mWidth, mWidth, 1.f, 1.f, mAngle, 0, 0, (int)mWidth, (int)mWidth, false, false);
		//mTexture.setRotation(mAngle);
		mTexture.draw(batch);
		//batch.draw(mTexture, mX, mY, mWidth, -mWidth);
	}
	public abstract boolean update(float delta);
	
	public void findSlope()
	{
		double radians = ((double)mAngle/180)*Math.PI;
		mDx = (float)(Math.cos(radians)*mSpeed);
		mDy = (float)(Math.sin(radians)*mSpeed);
		//System.out.println(radians);
		//System.out.println(mDx);
		//System.out.println(mDy);
		//System.out.println();
	}
	public float getAngle(float x,float y)
	{
		float deltaX = x-mX;
		float deltaY = y-mY;
		float angle = (float)(Math.atan2((double)deltaY,(double)deltaX)*(180.f/Math.PI));
		if(mAngle < 0)
		{
			angle = 360 -(-angle);
		}
		return angle;
	}
	public int getStrength()
	{
		return mStrength;
	}
	public int moveIfFree()
	{
		int id;
		int originalCollision;
		id = mOthers.collisionWithOthers(mX+mDx,mY+mDy,mId);
		originalCollision = id;
		if(id == -1)
		{
			mX+=mDx;
			mY+=mDy;
			return -1;
		}
		
		id = mOthers.collisionWithOthers(mX+mDx,mY,mId);
		if(id == -1)
		{
			
			return -1;
		}
		id = mOthers.collisionWithOthers(mX,mY+mDy,mId);
		if(id==-1)
		{
			
			return -1;
		}
		/*if(mOthers.mAnimals[originalCollision].getStrength()>mStrength)
		{
			Touch otherParent = mOthers.mAnimals[originalCollision].getParent();
			mParent.mX = otherParent.mX;
			mParent.mY = otherParent.mY;
			mParent.mPointer = otherParent.mPointer;
		}
		else
		{
			mOthers.mAnimals[originalCollision].pleaseMoveX(mDx, mId);
			mOthers.mAnimals[originalCollision].pleaseMoveY(mDy, mId);
			mX+=mDx*.5f;
			mY+=mDy*.5f;
		}*/
		//System.out.println("we didnt move!");
		return id;
	}
	public Touch getParent()
	{
		return mParent;
	}
	public float pleaseMoveX(float x,int other)
	{
		int id;
		id = mOthers.collisionWithOthers(mX+x, mY, mId);
		if(id !=other && id != mId && id !=-1)
		{
			
			return 0.f;
		}
		mX+=x;
		return x;
	}
	public float pleaseMoveY(float y,int other)
	{
		int id;
		id = mOthers.collisionWithOthers(mX, mY+y, mId);
		mY +=y;
		if(id !=other && id != mId && id !=-1)
		{
			//mOthers.mAnimals[id].pleaseMoveY(y,mId);
			return 0.f;
		}
		mY+=y;
		return y;
	}
		
	public void addTouch(float x,float y,int pointer)
	{
		//System.out.println("in addTouch");
		if(mParent.mPointer == pointer)
		{
			mParent.mX = x;
			mParent.mY = y;
			return;
		}
		if(mParent.mPointer == -1)
		{
			mParent.mX = x;
			mParent.mY = y;
			mParent.mPointer = pointer;
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
