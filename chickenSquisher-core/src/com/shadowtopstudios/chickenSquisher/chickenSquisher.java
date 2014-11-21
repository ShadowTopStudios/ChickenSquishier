package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class chickenSquisher extends Game {
	GameScene mGameScene;
	
	
	@Override
	public void create () {
		mGameScene = new GameScene(this);
		setScreen(mGameScene);
	}

}
