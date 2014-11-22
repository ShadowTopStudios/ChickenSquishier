package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shadowtopstudios.chickenSquisher.World.AnimalType;
import com.shadowtopstudios.chickenSquisher.World.WorldType;

public class chickenSquisher extends Game {
	GameScene mGameScene;
	MenuScene mMenuScene;
	public static final int CAMERA_WIDTH=80;
	public static final int CAMERA_HEIGHT=60;
	public AnimalType animal = AnimalType.chick;
	public WorldType level = WorldType.grass;
	
	@Override
	public void create () {
		mGameScene = new GameScene(this);
		mMenuScene = new MenuScene(this);
		
		setScreen(mMenuScene);
	}
	public void switchToMenu()
	{
		setScreen(mMenuScene);
	}
	public void newGame()
	{
		mGameScene=new GameScene(this);
		setScreen(mGameScene);
	}
	public void resumeGame()
	{
		setScreen(mGameScene);
	}
	public void quit()
	{
		Gdx.app.exit();
	}

}