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

public class Win extends JFrame implements ActionListener {

    JButton continu = new JButton();
    JButton exit = new JButton();

    FPSAnimator animator;

    Win win;
    Audio audio ;
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new Win();
    }

    public Win() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        GLCanvas glcanvas;

        audio =new Audio("Assets\\project\\vo_bugs_announcer_gamefinished_victory WINNING.wav");
        WinGLEventListener listener = new WinGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        // glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);

        // add(sound);
        add(continu);
        add(exit);
        

        // sound.setFocusable(false);
        continu.setFocusable(false);
        exit.setFocusable(false);
        

        // sound.setBounds(557, 15, 51, 43);
        continu.setBounds(100, 380, 170, 50);
        exit.setBounds(370, 380, 170, 50);
        

        // ImageIcon soundButton = new ImageIcon("D:\\compgraphics\\Assets\\project\\sound.png");
        ImageIcon continuButton = new ImageIcon("Assets\\project\\continue.jpeg");
        ImageIcon exitButton = new ImageIcon("Assets\\project\\exit.jpeg");
        
        //sound.setIcon(soundButton);
        continu.setIcon(continuButton);
        exit.setIcon(exitButton);
        
        // sound.addActionListener(this);
        continu.addActionListener(this);
        exit.addActionListener(this);
        

        // sound.setActionCommand("1");
        continu.setActionCommand("1");
        exit.setActionCommand("2");
       

        add(glcanvas, BorderLayout.CENTER);

        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        setResizable(false);
        setTitle("WINNER");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "1") {

            dispose();
            audio.clip.stop();
            try {
                new StartButton();
                
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Win.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Win.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Win.class.getName()).log(Level.SEVERE, null, ex);
            }

           
        } else if (e.getActionCommand() == "2") {

            dispose();
            audio.clip.stop();     
            try {
                //easy frame
                new BunnyGame();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(StartButton.class.getName()).log(Level.SEVERE, null, ex);
            }
            

           

        

    }

}






}