package com.shadowtopstudios.chickenSquisher;

public class TestController
{
	public Scene mWorld;
	public TestController(Scene w)
	{
		mWorld = w;
	}
	public void addTouch(float x,float y,int pointer)
	{
		int id=0;
		for(int i =0;i<mWorld.mButtons.length;i++)
		{
			System.out.println(i);
			id=mWorld.mButtons[i].contains(x, y);
			if(id != -1)
			{
				mWorld.buttonPressed(id);
			}
			System.out.println("ok");
		}
		mWorld.addTouch(x,y,pointer);
		System.out.println("okay");
		return;
		
	}
	public void deleteTouch(int pointer)
	{
		if(mWorld.mWorld !=null)
		{
			mWorld.mWorld.deleteTouch(pointer);
		}
	}
}
