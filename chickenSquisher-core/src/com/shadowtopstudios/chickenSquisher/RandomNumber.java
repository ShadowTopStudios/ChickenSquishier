package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.math.RandomXS128;

public class RandomNumber {
	static public RandomXS128 random = new RandomXS128();
	
	static public int random (int start, int end) {
		return start + random.nextInt(end - start + 1);
	}
	
}
