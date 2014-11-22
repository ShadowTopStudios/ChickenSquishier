package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shell extends Particle
{
	public float alphaRate;
	public Shell()
	{
		rand = new Random();
		mOffsetX =-11+ (float)rand.nextInt(22);
		mOffsetY = -11+(float)rand.nextInt(22);
		mAngle = (float) rand.nextInt(360);
		mSpeed = 0.1f+(float) rand.nextInt(10)/100.f;
		mSpeedMax = mSpeed;
		
		mWidth = 2.f;
		mHeight = 4.f;
		mTexture = new Texture(Gdx.files.internal("broken_shell.png"));
		mSprite = new Sprite(mTexture);
		this.mSprite.setSize(this.mWidth, this.mHeight);
		mSprite.setOriginCenter();
		//alphaRate = .01f+(float) rand.nextInt(10)/30.f;
		mTimerMax =50+rand.nextInt(20);
		mTimer = mTimerMax;
		mAlpha = 1.f;
	}
	@Override
	public boolean update(float delta) {
		mAngle = (mAngle-2)+rand.nextInt(4);
		mTimer -=1;
		mAlpha -=.01f;
		mSpeed-=.005f;
		if(mSpeed <=0)
		{
			mSpeed = 0;
		}
		if(mTimer <=0)
		{
			mAlive =false;
			return mAlive;
		}
		findSlope();
		mX+=mDx;
		mY+=mDy;
		mSprite.setAlpha(mAlpha);
		mSprite.setRotation(mAngle-90);
		mSprite.setPosition(mX, mY);
		return mAlive;
	}


}
