package main.java;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

public class Sound {
	Clip musicClip;
	URL url[] = new URL[1];

	public Sound() {
		url[0] = getClass().getResource("/wt_bg_music.wav");
	}

	public void play(int i, boolean music) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url[i]);
			Clip clip = AudioSystem.getClip();

			if (music) {
				musicClip = clip;
			}

			clip.open(audioInputStream);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == Type.STOP)
						clip.close();
				}
			});

			audioInputStream.close();
			clip.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void loop() {
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		musicClip.stop();
		musicClip.close();
	}
}
