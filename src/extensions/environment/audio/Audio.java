package extensions.environment.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Hashtable;
import java.io.File;
import java.lang.Runnable;
import java.util.concurrent.Executors;

public class Audio{
    private Hashtable<String,Clip> sounds = new Hashtable<>();
    public Audio(){
       loadAudio("assets/Audio/jump.wav","jump"); 
       loadAudio("assets/Audio/background.wav","background"); 
    }

    public void loadAudio(String fileName,String ressourceName){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            sounds.put(ressourceName,clip);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public void play(String ressourceName){
        Executors.newSingleThreadExecutor().execute(new Runnable() { // On lance le son dans un thread pour éviter un freeze du jeu qui arrive une fois sur 2
            @Override
            public void run() {
                Clip clip = sounds.get(ressourceName);
                clip.setFramePosition(0);
                clip.start();
            }
        });
    }
    
}
