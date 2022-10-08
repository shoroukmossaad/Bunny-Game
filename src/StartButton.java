package BunnyGame;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class StartButton  extends JFrame implements ActionListener {

   // JButton sound = new JButton();
    JButton home = new JButton();
    JButton easy = new JButton();
    JButton medium = new JButton();
    JButton hard = new JButton();
    
        Audio audio ;
  
          
      StartButton start ;
    
    FPSAnimator animator;

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new StartButton();
    }

    public StartButton() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        GLCanvas glcanvas;
         
       // audio =new Audio("D:\\compgraphics\\Assets\\project\\sound-external.wav");
         
         
         audio =new Audio("Assets\\project\\sound-external.wav");
        StartButtonGLEventListener listener = new StartButtonGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        // glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);

       // add(sound);
        add(home);
        add(easy);
        add(medium);
        add(hard);

       // sound.setFocusable(false);
        home.setFocusable(false);
        easy.setFocusable(false);
        medium.setFocusable(false);
        hard.setFocusable(false);

       // sound.setBounds(557, 15, 51, 43);
        home.setBounds(557, 15, 50, 43);
        easy.setBounds(220, 150, 170, 45);
        medium.setBounds(220, 200, 170, 45);
        hard.setBounds(220, 250, 170, 45);

       // ImageIcon soundButton = new ImageIcon("D:\\compgraphics\\Assets\\project\\sound.png");
        ImageIcon homeButton = new ImageIcon("Assets\\project\\home.png");
        ImageIcon easyButton = new ImageIcon("Assets\\project\\easy.png");
        ImageIcon mediumButton = new ImageIcon("Assets\\project\\medium.png");
        ImageIcon hardButton = new ImageIcon("Assets\\project\\hard.png");

        //sound.setIcon(soundButton);
        home.setIcon(homeButton);
        easy.setIcon(easyButton);
        medium.setIcon(mediumButton);
        hard.setIcon(hardButton);

       // sound.addActionListener(this);
        home.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

       // sound.setActionCommand("1");
        home.setActionCommand("1");
        easy.setActionCommand("2");
        medium.setActionCommand("3");
        hard.setActionCommand("4");

        add(glcanvas, BorderLayout.CENTER);

        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        audio.clip.loop(Clip.LOOP_CONTINUOUSLY);
        setResizable(false);
        setTitle("Level");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "1") {
            
         this.dispose();
         
         audio.clip.stop();
            try {
                new BunnyGame();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
        } else if (e.getActionCommand() == "2") {
              
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
            
             audio.clip.stop();
           //         this.audio.clip.stop();
            try {
                //easy frame
                new mainOfEasy();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            }
            //dispose();
            
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
            
            
        } else if (e.getActionCommand() == "3") {
            
             audio.clip.stop();
            
            try {
                // medium frame
                new mainOfMeduim();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            }
//            dispose();
        }
         else if (e.getActionCommand() == "4") {
             
              audio.clip.stop();
            try {
                // hard frame
                new mainOfHard();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            }
           //
           dispose();
        }
        
        

    }

}




