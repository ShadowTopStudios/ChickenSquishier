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
		//System.out.println(mX);
		System.out.println(mY);
		//System.out.println(x);
		System.out.println(y);
		System.out.println(y>mY);
		//System.out.println(mX+mWidth);
		System.out.println(mY+mHeight);
		System.out.println(y<(mY+mHeight));
		boolean haveX=false;
		boolean haveY=false;
		if(x > mX && x < (mX+mWidth))
		{
			System.out.println("made it through x");
			haveX = true;
		}
		if(y > mY && y < (mY+mHeight))
		{
			haveY = true;
			System.out.println("made itthrough Y");
			System.out.println("contained");
			System.out.println();
		}
		System.out.println("notContained");
		System.out.println();
		if(haveX && haveY)
		{
			return mId;
		}
		return -1;
	}
	public void draw(SpriteBatch b)
	{
		b.draw(mTexture,mX,mY,mWidth,mHeight);
		///draw text in middle of button
	}
}
