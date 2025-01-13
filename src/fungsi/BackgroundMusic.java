/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

/**
 * @author khanzasoft
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author Kanit SIRS
 */
public class BackgroundMusic {

    private static Player player;

    private final String filename;

    Thread playMusic;

    // constructor that takes the name of an MP3 file
    /**
     * @param filename
     */
    public BackgroundMusic(String filename) {
        this.filename = filename;
    }

    // play the MP3 file to the sound card
    /**
     *
     */
    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Problem playing file " + filename);
        }
    }

    /**
     *
     */
    public void start() {
        play();
        playMusic = new Thread(new PlayMusic());
        playMusic.start();
    }

    public void stop() {
        close();
        playMusic = null;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    class PlayMusic implements Runnable {

        @Override
        public void run() {
            try {
                player.play();
            } catch (JavaLayerException e) {
            }
        }

    }

    private static final Logger LOG = Logger.getLogger(BackgroundMusic.class.getName());

}
