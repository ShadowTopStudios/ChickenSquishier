package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shadowtopstudios.chickenSquisher.World.AnimalType;
import com.shadowtopstudios.chickenSquisher.World.ParticleType;

public class AnimalContainer
{
	public static final int SIZE=20;
	public static final int SPACING = 12;
	public World mWorld;
	public Animal[] mAnimals;
	protected int[] mUpdateLoop;
	protected int mUpdateSize=SIZE;
	protected float mRadRadSquared=7;
	protected float mRadius;
	
	public AnimalContainer(World w,AnimalType type)
	{
		mWorld = w;
		mAnimals = new Animal[SIZE];
		mUpdateLoop = new int[SIZE];
		int index = 0;
		switch(type)
		{
		case chick:
			for(int y = 1;y<6;y++)
			{
				for(int x=1;x<5;x++)
				{
					mUpdateLoop[index]=index;
					mAnimals[index]=new Chick(x*SPACING,y*SPACING,this,index);
					index++;
				}
			}
			break;
		case bunny:
			for(int y = 1;y<6;y++)
			{
				for(int x=1;x<5;x++)
				{
					mUpdateLoop[index]=index;
					mAnimals[index]=new Bunny(x*SPACING,y*SPACING,this,index);
					index++;
				}
			}
			break;
		case turtle:
			for(int y = 1;y<6;y++)
			{
				for(int x=1;x<5;x++)
				{
					mUpdateLoop[index]=index;
					mAnimals[index]=new Turtle(x*SPACING,y*SPACING,this,index);
					index++;
				}
			}
			break;
		default:
			for(int y = 1;y<6;y++)
			{
				for(int x=1;x<5;x++)
				{
					mUpdateLoop[index]=index;
					mAnimals[index]=new Chick(x*SPACING,y*SPACING,this,index);
					index++;
				}
			}
			break;
		}
		mRadius = mAnimals[0].mRadius;
		//mUpdateLoop[index]=index;
		//mAnimals[index]=new Chick(30,30,this,index);
		//mUpdateSize = 1;
	}
	public int collisionWithOthers(float x,float y,float id)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			if(id !=mUpdateLoop[i])
			{
				if(mAnimals[mUpdateLoop[i]].distanceFromMe(x, y)<mRadRadSquared)
				{
					return mUpdateLoop[i];
				}
			}
		}
		return -1;
	}
	public void killChicks(float x,float y,float rad)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			if(mAnimals[mUpdateLoop[i]].distanceFromMe(x,y)<((rad+mRadius)*(rad+mRadius)))
			{
				mAnimals[mUpdateLoop[i]].mHp--;
				if(mAnimals[mUpdateLoop[i]].mHp <=0)
				{
					mAnimals[mUpdateLoop[i]].mAlive = false;
				}
				for(int j=0;j<5;j++)
				{
					mWorld.addParticle(mAnimals[mUpdateLoop[i]].mX,mAnimals[mUpdateLoop[i]].mY,ParticleType.feather);
				}
			}
		}
	}
	public void update(float delta)
	{
		int start = 0;
		boolean keepUpdating;
		while(start !=mUpdateSize)
		{
			keepUpdating = mAnimals[mUpdateLoop[start]].update(delta);
			if(!keepUpdating)
			{
				mUpdateLoop[start] = mUpdateLoop[mUpdateSize-1];
				mUpdateSize--;
			}
			else
			{
				start++;
			}
		}
	}
	public void draw(SpriteBatch batch)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			mAnimals[mUpdateLoop[i]].draw(batch);
		}
	}
	public void addTouch(float x,float y,int pointer)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			mAnimals[mUpdateLoop[i]].addTouch(x, y, pointer);
		}
	}
	public void deleteTouch(int pointer)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			mAnimals[mUpdateLoop[i]].deleteTouch(pointer);
		}
	}
}
