package com.shadowtopstudios.chickenSquisher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioController {
	
	public static Sound backgroundMusic;
	public static Sound androidSoundEffect;
	public static Sound soundEffect;//unused atm due to android complications
	
	public AudioController(){
		
	}
	
	public void loadAudio(){
		androidSoundEffect = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.ogg"));
	}
	
	public void playSoundEffect(String soundName, float volume){
	//	s0ound = Gdx.audio.newSound(Gdx.files.internal("audio/" + soundName + ".ogg"));
		androidSoundEffect.play(volume);
	}
	
	public void playBackgroundMusic(String soundName, float volume){
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("audio/" + soundName + ".ogg"));
		backgroundMusic.loop(volume);
	}
	
	public void pause(){
		backgroundMusic.pause();
	}
	
	public void resume(){
		backgroundMusic.resume();
	}
}
