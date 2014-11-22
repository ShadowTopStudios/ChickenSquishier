package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shadowtopstudios.chickenSquisher.World.AnimalType;
import com.shadowtopstudios.chickenSquisher.World.WorldType;

public class MenuScene extends Scene implements InputProcessor,Screen {

	public chickenSquisher mWrapper;
	protected SpriteBatch mHud;
	public float mPx;
	public float mPy;
	//public Button[]mMenuButtons;
	public static final float spacing = 20;
	//public TestController mController;
	public int mHeight;
	public int mWidth;
	public Texture mBackground;
	public MenuScene(chickenSquisher w)
	{
		mWrapper = w;
		mButtons = new Button[9];
		int index = 0;
		for(int i =1;i<4;i++)
		{
			mButtons[index] = new Button((i*spacing)-7,(float)mWrapper.CAMERA_HEIGHT-10.f,15.f,10.f,"menu",index,new Texture("button.png"));
			index++;
		}
		for(int i =1;i<4;i++)
		{
			mButtons[index] = new Button((i*spacing)-7,(float)mWrapper.CAMERA_HEIGHT-30.f,15.f,10.f,"menu",index,new Texture("button.png"));
			index++;
		}
		for(int i =1;i<4;i++)
		{
			mButtons[index] = new Button((i*spacing)-7,(float)mWrapper.CAMERA_HEIGHT-60.f,15.f,10.f,"menu",index,new Texture("button.png"));
			index++;
		}
		mHud = new SpriteBatch();
		mBackground = new Texture(Gdx.files.internal("Menu_image.png"));
		//mController = new TestController(this);
	}
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//mWorld.update(delta);
		//mWorld.draw();
		mHud.begin();
		mHud.setProjectionMatrix(mWrapper.mGameScene.mWorld.mCamera.combined);
		mHud.draw(mBackground,0,0,mWrapper.CAMERA_WIDTH,mWrapper.CAMERA_HEIGHT);
		for(int i = 0;i<9;i++)
		{
			mButtons[i].draw(mHud);
		}
		mHud.end();
		
	}

	@Override
	public void resize(int width, int height) {
		mHeight = height;
		mWidth = width;
		mPx = mWidth/mWrapper.CAMERA_WIDTH;
		mPy = mHeight/mWrapper.CAMERA_HEIGHT;
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x =(float)screenX/mPx;
		float y = (float)(mHeight-screenY)/mPy;
		int id=0;
		for(int i =0;i<9;i++)
		{
			System.out.println(i);
			id=mButtons[i].contains(x, y);
			if(id != -1)
			{
				buttonPressed(id);
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x =(float)screenX/mPx;
		float y = (float)(mHeight-screenY)/mPy;
		int id=0;
		for(int i =0;i<9;i++)
		{
			System.out.println(i);
			id=mButtons[i].contains(x, y);
			if(id != -1)
			{
				buttonPressed(id);
			}
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buttonPressed(int id) {
		switch(id)
		{
		case 0:
			mWrapper.newGame();
			break;
		case 1:
			mWrapper.resumeGame();
			break;
		case 2:
			mWrapper.quit();
			break;
		case 3:
			mWrapper.animal = AnimalType.chick;
			break;
		case 4:
			mWrapper.animal = AnimalType.turtle;
			break;
		case 5:
			mWrapper.animal = AnimalType.bunny;
			break;
		case 6:
			mWrapper.level = WorldType.grass;
			break;
		case 7:
			mWrapper.level = WorldType.desert;
			break;
		case 8:
			mWrapper.level = WorldType.ice;
			break;
			
		}
		
	}
	@Override
	public void addTouch(float x,float y,int pointer)
	{
		System.out.println("in addTouchMenu");
		return;
	}

}
