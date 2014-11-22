package com.shadowtopstudios.chickenSquisher;

//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MeteorsContainer {
	
	public static final int METEOR_ARRAY_SIZE = 100;
	
	protected Meteor[] meteorsArray;
	protected int[] randomMeteorArray;
	protected int mUpdateSize = 10;
	protected int activeMeteors = 10;
	protected World mWorld;
	protected float timer = 5;
	
	protected float timerSpeed = 0.1f;
	
	public MeteorsContainer(World world){//remove all instances of test
		mWorld = world;

		meteorsArray = new Meteor[METEOR_ARRAY_SIZE];
		randomMeteorArray = new int[10];
		mUpdateSize = 0;

		for(int i = 0; i < METEOR_ARRAY_SIZE; i++){
			float sSpeed = (float)RandomNumber.random(4,6)/10.f;//screen width
			float mSpeed = (float)RandomNumber.random(4,6)/10.f;//screen height
			int startSize = RandomNumber.random(65,75);
			int meteorSize = RandomNumber.random(40,50);
			int collisionSize = RandomNumber.random(15,20);
			int spawnX = RandomNumber.random(5,85);
			int spawnY = RandomNumber.random(5,55);
			meteorsArray[i] = new Meteor(i, sSpeed, mSpeed, startSize, spawnX, spawnY, meteorSize, collisionSize);	
		}
	}
	
	public void genMeteor()
	{
		if(mUpdateSize < 9)
		{
			int num = RandomNumber.random(0, METEOR_ARRAY_SIZE - 1);
			while(meteorsArray[num].keepUpdating)
			{
				num++;
				if(num >=METEOR_ARRAY_SIZE)
				{
					num=0;
				}
			}
			randomMeteorArray[mUpdateSize]=num;
			mUpdateSize++;
			meteorsArray[num].resetMeteor();
		}
	}
	public void update(float delta){
		timer-= timerSpeed;
		if(timer <= 0){
			genMeteor();
			timer = 10;
			if(timerSpeed < (timer / 3)){
				timerSpeed+=0.003f;
			}
		//	Gdx.app.log("aaaaaaahhh:", "" + timerSpeed);
		}
		
		int start = 0;
		boolean keepUpdating;
		while(start !=mUpdateSize)
		{
			keepUpdating = meteorsArray[randomMeteorArray[start]].update(delta);	
			if(!keepUpdating){
				mWorld.checkChicks(meteorsArray[randomMeteorArray[start]].spawnX, meteorsArray[randomMeteorArray[start]].spawnY, (meteorsArray[randomMeteorArray[start]].sizeX / 2));
				randomMeteorArray[start] = randomMeteorArray[mUpdateSize - 1];
				mUpdateSize--;
				
			}else{
				start++;
			}
			
		}	
	}
	
	public void draw(SpriteBatch mBatch){		
		for(int i = 0; i < mUpdateSize; i++){
			if(meteorsArray[randomMeteorArray[i]].switchToMeteor){
				meteorsArray[randomMeteorArray[i]].draw(mBatch);
			}
		}
	}
	
	public void drawShadow(SpriteBatch sBatch){
		for(int i = 0; i < mUpdateSize; i++){
			if(!meteorsArray[randomMeteorArray[i]].switchToMeteor){
				meteorsArray[randomMeteorArray[i]].drawShadow(sBatch);
			}
		}
	}
}

//Gdx.app.log("aaaaaaahhh:", "" + test);
