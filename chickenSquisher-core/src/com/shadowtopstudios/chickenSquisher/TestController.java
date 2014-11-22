package com.shadowtopstudios.chickenSquisher;

public class TestController
{
	public World mWorld;
	public TestController(World w)
	{
		mWorld = w;
	}
	public void addTouch(float x,float y,int pointer)
	{
		//System.out.println(x);
		mWorld.addTouch(x,y,pointer);
	}
	public void deleteTouch(int pointer)
	{
		mWorld.deleteTouch(pointer);
	}
}
