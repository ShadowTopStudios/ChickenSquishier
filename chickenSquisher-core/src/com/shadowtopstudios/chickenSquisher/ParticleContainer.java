package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shadowtopstudios.chickenSquisher.World.ParticleType;

public class ParticleContainer
{
	public static final int PARTICLE_SIZE=100;
	protected Particle[] mParticles;
	protected int[] mUpdateLoop;
	protected int mUpdateSize=0;
	protected World mWorld;
	protected ParticleType type;
	public ParticleContainer(World w,ParticleType type)
	{
		mWorld = w;
		mParticles = new Particle[PARTICLE_SIZE];
		mUpdateLoop = new int[PARTICLE_SIZE];
		switch(type)
		{
		case feather:
			for(int i = 0;i<PARTICLE_SIZE;i++)
			{
				mParticles[i]=new Feather();
			}
			break;
		case rock:
			for(int i = 0;i<PARTICLE_SIZE;i++)
			{
				mParticles[i]=new Rock();
			}
			break;
		case shell:
			for(int i = 0;i<PARTICLE_SIZE;i++)
			{
				mParticles[i]=new Shell();
			}
			break;
		case fluff:
			for(int i = 0;i<PARTICLE_SIZE;i++)
			{
				mParticles[i]=new Fluff();
			}
			break;
		default:
			for(int i = 0;i<PARTICLE_SIZE;i++)
			{
				mParticles[i]=new Feather();
			}
			break;
		}
	}
	public void update(float delta)
	{
		int start = 0;
		boolean keepUpdating;
		while(start !=mUpdateSize)
		{
			keepUpdating = mParticles[mUpdateLoop[start]].update(delta);
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
	public void addParticle(float x,float y)
	{
		if(mUpdateSize < PARTICLE_SIZE)
		{
			int num = RandomNumber.random(0, PARTICLE_SIZE - 1);
			int oldnum=num;
			while(mParticles[num].mAlive)
			{
				num++;
				if(num >=PARTICLE_SIZE)
				{
					num=0;
				}
				if(num==oldnum)
				{
					return;
				}
			}
			mUpdateLoop[mUpdateSize]=num;
			mUpdateSize++;
			mParticles[num].reset(x,y);
		}
	}
	public void draw(SpriteBatch batch)
	{
		for(int i=0;i<mUpdateSize;i++)
		{
			mParticles[mUpdateLoop[i]].draw(batch);
		}
	}
	
}
