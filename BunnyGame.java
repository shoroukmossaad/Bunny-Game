package BunnyGame;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
//, WindowConstants
public class BunnyGame extends JFrame implements ActionListener {

    JButton start = new JButton();
    JButton howToPlay = new JButton();
    JButton scores = new JButton();
    JButton exit = new JButton();
    JButton sound = new JButton();
    JButton closedSound = new JButton();
    
    FPSAnimator animator;
     Audio audio ;
    BunnyGame bunny;
    
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new BunnyGame();
        
        
    }
    
   

    public BunnyGame() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        GLCanvas glcanvas;
        
         audio =new Audio("Assets\\project\\sound-external.wav");
        BunnyGameGLEventListener listener = new BunnyGameGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        
        listener.setCanvas(glcanvas);

        add(start);
        add(howToPlay);
        add(scores);
        add(exit);
        add(sound);
        add(closedSound);
        
        start.setFocusable(false);
        howToPlay.setFocusable(false);
        scores.setFocusable(false);
        exit.setFocusable(false);
        sound.setFocusable(false);
        closedSound.setFocusable(false);
        
        start.setBounds(130, 220, 170, 45);
        howToPlay.setBounds(130, 280, 170, 45);
        scores.setBounds(130, 340, 170, 45);
        exit.setBounds(130, 400, 170, 45);
        sound.setBounds(910, 15, 51, 43);
        
        
        
      //  closedSound.setBounds(910, 15, 51, 43);
        
        ImageIcon startButton = new ImageIcon("Assets\\project\\play.png");
        ImageIcon howToPlayButton = new ImageIcon("Assets\\project\\howToPlay.png");
        ImageIcon scoresButton = new ImageIcon("Assets\\project\\scores.png");
        ImageIcon exitButton = new ImageIcon("Assets\\project\\exit.png");
        ImageIcon soundButton = new ImageIcon("Assets\\project\\sound.png");
        ImageIcon closedSoundButton = new ImageIcon("Assets\\project\\closedSound.jpeg");
        
        
                
        start.setIcon(startButton);
        howToPlay.setIcon(howToPlayButton);
        scores.setIcon(scoresButton);
        exit.setIcon(exitButton);
        sound.setIcon(soundButton);
        closedSound.setIcon(closedSoundButton);
        
        
        start.addActionListener(this);
        howToPlay.addActionListener(this);
        scores.addActionListener(this);
        exit.addActionListener(this);
        sound.addActionListener(this);
        closedSound.addActionListener(this);
        
        start.setActionCommand("1");
        howToPlay.setActionCommand("2");
        scores.setActionCommand("3");
        exit.setActionCommand("4");
        sound.setActionCommand("5");
        closedSound.setActionCommand("6");

        add(glcanvas, BorderLayout.CENTER);

        
        audio.clip.loop(Clip.LOOP_CONTINUOUSLY);
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        setResizable(false);
        setTitle("Rabbit");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1020, 610);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "1") {
            
           this.dispose();
           audio.clip.stop();
           //setVisible(false);
           
            try {
//                   setVisible(false);
                new StartButton();
                
                
                
            } catch (LineUnavailableException ex) {
                Logger.getLogger(BunnyGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BunnyGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(BunnyGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          //  this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_DEACTIVATED));
            
        } 
        
        else if (e.getActionCommand() == "2") {
            new HowToPlayButton();
//            setVisible(false);
        }
        
        else if (e.getActionCommand() == "3") {
            new ScoreButton();
        }
        
        else if (e.getActionCommand() == "4") {
            
         this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
         System.exit(0);
            
          
        }else if (e.getActionCommand() == "5") {
            
           closedSound.setBounds(910, 15, 51, 43); 
           sound.setBounds(1020, 0, 0, 0);
           audio.clip.stop();
          
        }else if(e.getActionCommand() == "6") {
            
            sound.setBounds(910, 15, 51, 43);
            closedSound.setBounds(1020, 0, 0, 0);
            audio.clip.start();
            audio.clip.loop(Clip.LOOP_CONTINUOUSLY);
           
      }

   
    }
    
    
    

}
