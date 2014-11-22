package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Chick extends Animal
{
	public boolean mMoving=true;
	public static final int smallRad = 7;
	public static final int bigRad = 15;
	public int mMoveRad = smallRad;
	public int mTimer;
	
	public Chick(int x,int y,AnimalContainer a,int id)
	{
		this.rand = new Random();
		this.mX = (float)x;
		this.mY = (float) y;
		this.mOthers = a;
		this.mId = id;
		this.mStrength = id;
		this.mSpeed = 0.3f + ((float)rand.nextInt(10)/8.f);
		this.mRadius = 2.5f;
		this.mWidth = 5.f;
		this.mParent = new Touch(-1,-1,-1);
		this.getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		this.mTexture = new Texture(Gdx.files.internal("block.png"));
		mTimer = 120;
	}
		
	@Override
	public boolean update(float delta)
	{
		//System.out.println(this.mParent.mX);
		if(distanceFromParent(mMoveRad))
		{
			if(this.mParent.mPointer !=-1)
			{
				mMoving = false;
				mMoveRad = bigRad;
				
				return true;
			}
			else
			{
				mMoving=false;
				mMoveRad = bigRad;
				mTimer--;
				if(mTimer <=0)
				{
					getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
					mTimer = 100+rand.nextInt(40);
				}
			}
		}
		else
		{
			mMoving = true;
			mMoveRad = smallRad;
		}
		this.mAngle = this.getAngle(mParent.mX, mParent.mY);
		//System.out.println(this.mAngle);
		this.findSlope();
		int id = this.moveIfFree();
		if(this.mParent.mPointer == -1 && id !=-1)
		{
			getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		}
		return true;
		
	}

}
