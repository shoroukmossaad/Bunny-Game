/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BunnyGame;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class hardRules extends JFrame implements ActionListener {

    //JButton sound = new JButton();
    JButton home = new JButton();

    FPSAnimator animator;

    public static void main(String[] args) {
        new hardRules();

    }

    public hardRules() {
        GLCanvas glcanvas;

        hardRulesGLEventListener listener = new hardRulesGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        // glcanvas.addKeyListener(listener);
        listener.setCanvas(glcanvas);

        // add(sound);
        add(home);

        //  sound.setFocusable(false);
        home.setFocusable(false);

        //sound.setBounds(557, 15, 51, 43);
        home.setBounds(557, 15, 50, 43);

        //  ImageIcon soundButton = new ImageIcon("D:\\compgraphics\\Assets\\project\\sound.png");
        ImageIcon homeButton = new ImageIcon("Assets\\project\\home.png");

        // sound.setIcon(soundButton);
        home.setIcon(homeButton);

        //  sound.addActionListener(this);
        home.addActionListener(this);

        // sound.setActionCommand("1");
        home.setActionCommand("1");

        add(glcanvas, BorderLayout.CENTER);

        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        setResizable(false);
        setTitle("How To Play");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "1") {
            this.dispose();
        }

    }

}
