package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fluff extends Particle
{
	public float alphaRate;
	public float sizeRate;
	public Fluff()
	{
		rand = new Random();
		mOffsetX =-3+ (float)rand.nextInt(6);
		mOffsetY = -3+(float)rand.nextInt(6);
		mAngle = (float) rand.nextInt(360);
		mSpeed = 0.005f+(float) rand.nextInt(20)/100.f;
		mWidth = 2.5f;
		mHeight = 5.f;
		mTexture = new Texture(Gdx.files.internal("bunny_fluff.png"));
		mSprite = new Sprite(mTexture);
		this.mSprite.setSize(this.mWidth, this.mHeight);
		mSprite.setOriginCenter();
		alphaRate = .0015f+(float) rand.nextInt(20)/100.f;
		mAlpha = 1.f;
		mSize = 1.f;
		sizeRate = .05f +(float) rand.nextInt(4)/10.f;
	}
	@Override
	public boolean update(float delta) {
		mAngle = (mAngle-7)+rand.nextInt(14);
		mAlpha -=alphaRate;
		mSize+=sizeRate;
		if(mAlpha <=0)
		{
			mAlive =false;
			return mAlive;
		}
		findSlope();
		mX+=mDx;
		mY+=mDy;
		mSprite.setAlpha(mAlpha);
		mSprite.setScale(mSize);
		mSprite.setRotation(mAngle-90);
		mSprite.setPosition(mX, mY);
		return mAlive;
	}

}
