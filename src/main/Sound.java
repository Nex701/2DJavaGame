package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
	
		soundURL[0] = getClass().getResource("/sound/DayTime.wav");
		soundURL[1] = getClass().getResource("/sound/NightTime.wav");
		soundURL[2] = getClass().getResource("sound/KeySoundEffect.wav");
		soundURL[3] = getClass().getResource("/sound/sound02.wav");
		soundURL[4] = getClass().getResource("/sound/sound03.wav");
		soundURL[5] = getClass().getResource("/sound/sound04.wav");
		soundURL[6] = getClass().getResource("/sound/sound05.wav");
		soundURL[7] = getClass().getResource("/sound/sound06.wav");
		soundURL[8] = getClass().getResource("/sound/sound07.wav");
		soundURL[9] = getClass().getResource("/sound/sound08.wav");
		soundURL[10] = getClass().getResource("/sound/sound09.wav");
		soundURL[11] = getClass().getResource("/sound/sound01.wav");
		soundURL[12] = getClass().getResource("/sound/GameplayMusic.wav");
		soundURL[13] = getClass().getResource("/sound/damage01.wav");
		soundURL[14] = getClass().getResource("/sound/damage02.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-33.0f); // Reduce volume by 10 decibels.
				clip.start();
		}
		catch(Exception e) {
			
		}
				
			
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}
}
