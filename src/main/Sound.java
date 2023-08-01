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
		soundURL[2] = getClass().getResource("/sound/KeySoundEffect.wav");
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
