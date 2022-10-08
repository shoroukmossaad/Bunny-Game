package BunnyGame;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.*;
import javax.swing.*;

public class ScoreButton extends JFrame implements ActionListener {

//    JButton sound = new JButton();
    JButton home = new JButton();
    JLabel highScore;
    JLabel lastScore;

    FPSAnimator animator;

    public static void main(String[] args) {
        new ScoreButton();
    }

    public ScoreButton() {

        this.lastScore = new JLabel();
        this.highScore = new JLabel();
        GLCanvas glcanvas;

        ScorButtonGLEventListener listener = new ScorButtonGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        // glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);
//       
//        add(sound);
        add(home);
        add(highScore);
        add(lastScore);
//        sound.setFocusable(false);
        home.setFocusable(false);
        highScore.setFocusable(false);
        lastScore.setFocusable(false);

//        sound.setBounds(557, 15, 51, 43);
        home.setBounds(557, 15, 50, 43);
        highScore.setBounds(200, 180, 220, 40);
        lastScore.setBounds(200, 285, 220, 40);    //

        ImageIcon homeButton = new ImageIcon("Assets\\project\\home.png");

        // sound.setIcon(soundButton);
        home.setIcon(homeButton);

        //sound.addActionListener(this);
        home.addActionListener(this);

//        highScore.
//        lastScore.  
        // sound.setActionCommand("1");
        home.setActionCommand("1");

        add(glcanvas, BorderLayout.CENTER);
        //scores.addActionListener(this);
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        setResizable(false);
        setTitle("Scores");
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    ScoreButton(int currentScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "1") {
            this.dispose();
        }

    }

}
