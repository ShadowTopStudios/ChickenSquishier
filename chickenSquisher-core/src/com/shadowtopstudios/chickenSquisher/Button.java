package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button
{
	protected float mX;
	protected float mY;
	protected float mWidth;
	protected float mHeight;
	protected String mLabel;
	protected int mId;
	protected Texture mTexture;
	
	public Button(float x,float y,float w,float h,String label,int id,Texture tex)
	{
		mX = x;
		mY = y;
		mWidth = w;
		mHeight = h;
		mLabel = label;
		mId = id;
		mTexture = tex;
	}
	public int contains(float x,float y)
	{
		if(x > mX && x < (mX+mWidth))
		{
			if(y > mY && y < (mY+mHeight))
			{
				return mId;
			}
		}
		return -1;
	}
	public void draw(SpriteBatch b)
	{
		b.draw(mTexture,mX,mY,mWidth,mHeight);
		///draw text in middle of button
	}
}
