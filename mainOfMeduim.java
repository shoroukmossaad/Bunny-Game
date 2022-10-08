/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BunnyGame;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCanvas;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class mainOfMeduim extends JFrame implements ActionListener {

    JButton home = new JButton();
    JButton rules = new JButton();
    JButton sound = new JButton();
    JButton closedsound = new JButton();
    JButton pause = new JButton();
    JButton resume = new JButton();

//        Animator animator;
    Audio audio1;
    // الصوت
    MediumGLEventListener listener = new MediumGLEventListener();
    public Font font1 = new Font("Arial", Font.PLAIN, 40);

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        new mainOfMeduim();
    }

    public mainOfMeduim() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        GLCanvas glcanvas;

        audio1 = new Audio("Assets\\project\\sound-external.wav");

        listener.counterText = new JLabel("");
        listener.counterText.setBounds(390, 15, 100, 50);
        listener.counterText.setFont(font1);

        listener.ShowCurrentScore = new JLabel("   0");
        listener.ShowCurrentScore.setBounds(520, 15, 100, 50);
        listener.ShowCurrentScore.setFont(font1);

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);

        glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);

        add(listener.counterText);
        add(listener.ShowCurrentScore);
        add(glcanvas, BorderLayout.CENTER);

        add(home);
        add(rules);
        add(sound);
        add(closedsound);
        add(pause);
        add(resume);

        home.setFocusable(false);
        rules.setFocusable(false);
        sound.setFocusable(false);
        closedsound.setFocusable(false);
        pause.setFocusable(false);
        resume.setFocusable(false);

        home.setBounds(50, 15, 51, 45);
        rules.setBounds(120, 15, 51, 45);
        sound.setBounds(910, 15, 51, 45);
        pause.setBounds(840, 15, 51, 45);

        ImageIcon homeButton = new ImageIcon("Assets\\project\\home.jpeg");
        ImageIcon rulesButton = new ImageIcon("Assets\\project\\rules.jpeg"); //
        ImageIcon soundButton = new ImageIcon("Assets\\project\\sound.jpeg");
        ImageIcon closedSoundButton = new ImageIcon("Assets\\project\\closedSound.jpeg");
        ImageIcon pauseButton = new ImageIcon("Assets\\project\\pause.jpeg");
        ImageIcon resumeButton = new ImageIcon("Assets\\project\\resume.jpeg");

        home.setIcon(homeButton);
        rules.setIcon(rulesButton);
        sound.setIcon(soundButton);
        closedsound.setIcon(closedSoundButton);
        pause.setIcon(pauseButton);
        resume.setIcon(resumeButton);

        home.addActionListener(this);
        rules.addActionListener(this);
        sound.addActionListener(this);
        closedsound.addActionListener(this);
        pause.addActionListener(this);
        resume.addActionListener(this);

        home.setActionCommand("1");
        rules.setActionCommand("2");
        sound.setActionCommand("3");
        closedsound.setActionCommand("4");
        pause.setActionCommand("5");
        resume.setActionCommand("6");

        add(glcanvas, BorderLayout.CENTER);
        listener.animator = new FPSAnimator(60);
        listener.animator.add(glcanvas);
        listener.animator.start();
        setResizable(false);
        setTitle("Meduim Level");
        setSize(1020, 610);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
        audio1.clip.loop(Clip.LOOP_CONTINUOUSLY);

        listener.counterText.setText("0:30");
        listener.ShowCurrentScore.setText("    0");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "1") {

            this.dispose();
            try {
                new BunnyGame();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(mainOfEasy.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mainOfEasy.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(mainOfEasy.class.getName()).log(Level.SEVERE, null, ex);
            }

            audio1.clip.stop();

        } else if (e.getActionCommand() == "2") {
            //rules
            new mediumRules();
        } else if (e.getActionCommand() == "3") {

            closedsound.setBounds(910, 15, 51, 43);
            sound.setBounds(1020, 0, 0, 0);
            audio1.clip.stop();

        } else if (e.getActionCommand() == "4") {

            sound.setBounds(910, 15, 51, 43);
            closedsound.setBounds(1020, 0, 0, 0);
            audio1.clip.start();
            audio1.clip.loop(Clip.LOOP_CONTINUOUSLY);

        } else if (e.getActionCommand() == "5") {
            //pause
            resume.setBounds(840, 15, 51, 45);
            pause.setBounds(1020, 0, 0, 0);
            //this.setEnabled(false);
            audio1.clip.stop();
            if (listener.animator.isAnimating()) {
                listener.animator.stop();
            }

        } else if (e.getActionCommand() == "6") {
            pause.setBounds(840, 15, 51, 45);
            resume.setBounds(1020, 0, 0, 0);

            //this.setEnabled(false);
            audio1.clip.start();
            audio1.clip.loop(Clip.LOOP_CONTINUOUSLY);
            if (!listener.animator.isAnimating()) {
                listener.animator.start();
            }

        }

    }

}
