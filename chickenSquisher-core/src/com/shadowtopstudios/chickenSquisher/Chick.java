package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chick extends Animal
{
	
	public Chick(int x,int y,AnimalContainer a,int id)
	{
		this.rand = new Random();
		this.mX = (float)x;
		this.mY = (float) y;
		this.mOthers = a;
		this.mId = id;
		this.mStrength = id;
		this.mSpeed = 0.2f + ((float)rand.nextInt(4)/10.f);
		this.mRadius = 1.5f;
		this.mWidth = 6.f;
		this.mParent = new Touch(-1,-1,-1);
		this.getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		this.mTexture = new Sprite(new Texture(Gdx.files.internal("chickie.png")));
		this.mTexture.setSize(this.mWidth, this.mWidth);
		mTexture.setOriginCenter();
		mTimer = 120;
		mSkitterMax = 20+rand.nextInt(20);
		mSkitter = mSkitterMax;
	}
		
	@Override
	public boolean update(float delta)
	{
		//System.out.println(this.mParent.mX);
		
		if(distanceFromParent(mMoveRad))
		{
			mMoving = false;
			mMoveRad = bigRad;
				
			mTimer--;
			if(mTimer <=0)
			{
				if(mParent.mPointer == -1)
				{
					getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
				}
				mTimer = 80+rand.nextInt(30);
			}
			return mAlive;
		}
		else
		{
			mMoving = true;
			mMoveRad = smallRad;
		}
		
		this.mAngle = (this.mAngle-7)+rand.nextInt(14);
		this.findSlope();
		int id = this.moveIfFree();
		if(id != -1 && this.mOthers.mAnimals[id].getStrength() > mStrength)
		{
			Touch other = this.mOthers.mAnimals[id].getParent();
			this.mParent.mX = other.mX-2 +rand.nextInt(4);
			this.mParent.mY = other.mY-2 +rand.nextInt(4);
			this.mParent.mPointer = other.mPointer;
		}
		if(this.mParent.mPointer == -1 && id !=-1)
		{
			getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		}
		//mTexture.setPosition(0, 0);
		mTexture.setRotation(mAngle-90);
		
		mTexture.setPosition(mX, mY);
		mSkitter--;
		if(mSkitter <=0)
		{
			mSkitter = mSkitterMax;
			this.mAngle = this.getAngle(mParent.mX, mParent.mY);
		}
		return mAlive;
		
	}

}
