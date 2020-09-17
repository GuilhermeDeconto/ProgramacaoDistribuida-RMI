package com.company;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	public static Clips tossacoin= load("/tossacoin.wav",1);
	
	
	
	public static void stopAll() {
		
		
	}
	
	public static class Clips {
		public Clip[] clips;
		private int p;
		private int count;
		private int framePosition;
		
		
		
		public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			if (buffer==null) 
				return;
			clips = new Clip[count];
			this.count=count;
			this.framePosition=0;
			for (int i=0;i<count;i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
		}
		public void play() {
			if (clips==null) return;
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;
			if (p==count) p=0;
		}
		public void pause() {
			if (clips==null) return;
			clips[p].stop();
			framePosition=clips[p].getFramePosition();
			
		}
		public void stop() {
			if (clips==null) return;
			clips[p].stop();
			clips[p].setFramePosition(0);
			
		}
		public void resume() {
			if (clips==null) return;
			clips[p].stop();
			clips[p].setFramePosition(framePosition);
			clips[p].start();
			
		}
		public void loop() {
			if (clips==null) return;
			clips[p].loop(300);
		}
		
	}

	

	
	
	private static Clips load(String name, int count) {
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			DataInputStream dis=new DataInputStream(Sound.class.getResourceAsStream(name));
		
		byte[] buffer= new byte[1024];
		int read =0;
		while((read=dis.read(buffer))>=0) {
			baos.write(buffer,0,read);
		}
			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data,count);
		}catch(Exception e) {
			try {
				return new Clips(null,0);
			}catch(Exception ee){
				return null;
			}
		}
	}
	
}


