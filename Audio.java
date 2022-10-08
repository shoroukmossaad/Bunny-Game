package BunnyGame;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
             Clip clip;
    public Audio(String path) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        
        File file = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        
    }

}
