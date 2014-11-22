package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioController {
	
	public static Sound sound;
	public static Sound androidSound;
	
	public AudioController(){
		
	}
	
	public void loadAudio(){
		androidSound = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.ogg"));
	}
	
	public void playSoundEffect(String soundName, float volume){
	//	sound = Gdx.audio.newSound(Gdx.files.internal("audio/" + soundName + ".ogg"));
		androidSound.play(volume);
	}
	
	public void playBackgroundMusic(String soundName, float volume){
		sound = Gdx.audio.newSound(Gdx.files.internal("audio/" + soundName + ".ogg"));
		//long id = sound.play(volume);
		sound.loop(volume);
		//sound.play(volume);
	}
	
	public void pause(){
		sound.pause();
	}
	
	public void resume(){
		sound.resume();
	}
}
