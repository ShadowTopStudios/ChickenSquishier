package com.shadowtopstudios.chickenSquisher;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Chick extends Animal
{
	public Chick(int x,int y,AnimalContainer a,int id)
	{
		this.rand = new Random();
		this.mX = (float)x;
		this.mY = (float) y;
		this.mOthers = a;
		this.mId = id;
		this.mSpeed = 1.f + ((float)rand.nextInt(10)/10.f);
		this.mRadius = 2.5f;
		this.mParent = new Touch(-1,-1,-1);
		this.getRandomParent(mOthers.mWorld.CAMERA_WIDTH,mOthers.mWorld.CAMERA_HEIGHT);
		this.mTexture = new Texture(Gdx.files.internal("block.png"));
	}
		
	@Override
	public boolean update(float delta)
	{
		this.mAngle = this.getAngle(mParent.mX, mParent.mY);
		this.findSlope();
		this.moveIfFree();
		return true;
		
	}

}
