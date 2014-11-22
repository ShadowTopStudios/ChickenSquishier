package com.shadowtopstudios.chickenSquisher;

public abstract class Scene
{
	public Button[] mButtons;
	public World mWorld;
	public abstract void buttonPressed(int id);
	public abstract void addTouch(float x, float y, int pointer) ;

}
