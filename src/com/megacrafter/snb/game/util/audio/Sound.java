package com.megacrafter.snb.game.util.audio;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound implements Runnable {

    private static final int BUFFER_SIZE = 128000;
    private String soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private Thread thread = new Thread(this);
    public boolean running = false;

    public void run() {
        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();

        init(soundFile);
    }

    public Sound(String filename) {
        init(filename);
    }

    public void play() {
        stop();
        start();
    }

    public static void playIndependently(String file) {
        Sound snd = new Sound(file);
        snd.play();
        snd.stop();
    }

    private synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init(String filename) {
        soundFile = filename;
        try {
            audioStream = AudioSystem.getAudioInputStream(Sound.class.getResource(filename));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}