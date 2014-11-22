package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shadowtopstudios.chickenSquisher.World.AnimalType;
import com.shadowtopstudios.chickenSquisher.World.WorldType;

public class GameScene extends Scene implements InputProcessor,Screen{

	//protected World mWorld;
	public chickenSquisher wrapper;
	public float mPx;
	public float mPy;
	protected int mWidth;
	protected int mHeight;
	protected SpriteBatch mHud;
	protected TestController mController; // change to controller when done testing
	public GameScene(chickenSquisher w)
	{
		wrapper = w;
		mWorld = new World(this,wrapper.animal,wrapper.level);
		mButtons = new Button[1];
		mButtons[0] = new Button(2.f,(float)mWorld.CAMERA_HEIGHT-9.f,15.f,10.f,"menu",1,new Texture("block.png"));
		mController = new TestController(this);
		mHud = new SpriteBatch();
		
	}
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mWorld.update(delta);
		mWorld.draw();
		mHud.begin();
		mHud.setProjectionMatrix(mWorld.mCamera.combined);
		mButtons[0].draw(mHud);
		mHud.end();
	}

	@Override
	public void resize(int width, int height) {
		mHeight = height;
		mWidth = width;
		mPx = mWidth/mWorld.CAMERA_WIDTH;
		mPy = mHeight/mWorld.CAMERA_HEIGHT;
		
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
		mWorld.audioController.pause();
		
	}

	@Override
	public void resume() {
		mWorld.audioController.resume();
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
		mController.addTouch((float)screenX/mPx, (float)(mHeight-screenY)/mPy, pointer);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mController.deleteTouch(pointer);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mController.addTouch((float)screenX/mPx, (float)(mHeight-screenY)/mPy, pointer);
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
		pause();
		wrapper.switchToMenu();
		
	}
	@Override
	public void addTouch(float x,float y,int pointer)
	{
		mWorld.addTouch(x, y, pointer);
	}

}
