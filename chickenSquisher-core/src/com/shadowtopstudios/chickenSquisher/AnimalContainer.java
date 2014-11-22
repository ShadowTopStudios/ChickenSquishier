package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimalContainer
{
	public static final int SIZE=20;
	public static final int SPACING = 12;
	public World mWorld;
	public Animal[] mAnimals;
	protected int[] mUpdateLoop;
	protected int mUpdateSize=SIZE;
	protected float mRadRadSquared=3;
	
	public AnimalContainer(World w)
	{
		mWorld = w;
		mAnimals = new Animal[SIZE];
		mUpdateLoop = new int[SIZE];
		int index = 0;
		for(int y = 1;y<6;y++)
		{
			for(int x=1;x<5;x++)
			{
				mUpdateLoop[index]=index;
				mAnimals[index]=new Chick(x*SPACING,y*SPACING,this,index);
				index++;
			}
		}
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
