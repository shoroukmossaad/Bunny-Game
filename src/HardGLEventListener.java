package BunnyGame;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import com.sun.opengl.util.FPSAnimator;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JLabel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class HardGLEventListener implements GLEventListener, KeyListener {

    FPSAnimator animator;
    int score = 0;
    boolean Succeed = false;
    int yCollision[] = {-235, -70};
    int xCollision[] = {-480, 480};
    String playerDirection = "down";
    int animationIndex = 0;
    int maxWidth = 1020;
    int maxHeight = 610;
    int lives = 5;
    double xPosition = 0, yPosition = -70;
    public double x = -550, y = -245;
    int moveAway = 0;
    int randomX = 0, randomY = 0, randomX1 = 0, randomY1 = 0;
    int playerSpeed = 10;
    int playerNum = 1, playerCount = 0;
    long last, current;
    public JLabel ShowCurrentScore, livesLabel, counterText;
    javax.swing.Timer timer, timer1;
    JLabel wining;
    boolean eaten = false;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    String textureNames[] = {"project//5.jpg", "holes//h2.png", "holes//c.png"};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
//"player//backMove1.png", , , "player//backMove11.png",
    String texturePlayerNames[] = {"player//backMove2.png", "player//backMove3.png", "player//frontMove2.png", "player//frontMove3.png", "player//leftMove2.png", "player//leftMove3.png", "player//rightMove2.png", "player//rightMove3.png"};
    TextureReader.Texture texturep[] = new TextureReader.Texture[texturePlayerNames.length];
    int texturesP[] = new int[texturePlayerNames.length];

    int upperbound = 4;
    Random rand1, rand2;
    private int mouseX, mouseY;

    private GLCanvas glc;

    int arrX[] = {-325, 0, 315, 200};
    int arrY[] = {400, 0, 200, 315};

    public void init(GLAutoDrawable gld) {
        /*
     5 means gun in array pos
     x and y coordinate for gun 
         */
        mouseX = 5 * maxWidth;
        mouseY = 5 * maxHeight;
        last = System.currentTimeMillis();
        rand1 = new Random(); //instance of randomX class
        rand2 = new Random();
        //generate randomX values from 0-3
        randomY = rand1.nextInt((-70 - (-235) + 1)) + (-235);
        randomX = rand2.nextInt((480 - (-480) + 1)) + (-480);

        randomY1 = rand1.nextInt((-70 - (-235) + 1)) + (-235);
        randomX1 = rand2.nextInt((480 - (-480) + 1)) + (-480);

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        //gl.glColor3d(1.88, 0.3, 1.88);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        minute = 1;
        second = 30;
        countdownTimer();
        timer.start();

        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture("Assets" + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texturep[i]);
                new GLU().gluBuild2DMipmaps(GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

        gl.glGenTextures(texturePlayerNames.length, texturesP, 0);

        for (int i = 0; i < texturePlayerNames.length; i++) {
            try {
                texturep[i] = TextureReader.readTexture("Assets" + "//" + texturePlayerNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, texturesP[i]);

//                mipmapsFromPNG(gl, new GLU(), texturep[i]);
                new GLU().gluBuild2DMipmaps(GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texturep[i].getWidth(), texturep[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texturep[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);

    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL.GL_TEXTURE_2D);
        DrawBackground(gl);

        //3--carrots
        current = System.currentTimeMillis();
        if (timer.isRunning()) {
            if (current >= last + 500) {
                int rx = randomX;

                int ry = randomY;
                randomY = rand1.nextInt((-70 - (-235) + 1)) + (-235);
                randomX = rand2.nextInt((480 - (-480) + 1)) + (-480);

//                randomY1 = rand1.nextInt((-70 - (-235) + 1)) + (-235);
//                randomX1 = rand2.nextInt((480 - (-480) + 1)) + (-480);
                last = current;

                do {
                    // randomY = rand1.nextInt(upperbound);
                    randomY = rand1.nextInt((-70 - (-235) + 1)) + (-235);
                    randomX = rand2.nextInt((480 - (-480) + 1)) + (-480);
                } while (rx == randomX && ry == randomY);

//              
                eaten = false;
            }
            if (!eaten) {
                gl.glPushMatrix();
                DrawSprite(gl, randomX, randomY, 2, 300, 300);
                //DrawSprite(gl, randomX1, randomY1, 2, 300, 300);

                gl.glPopMatrix();
            }

        }

        if (timer.isRunning()) {
            if (!eaten && eqDistance(xPosition, yPosition, randomX, randomY) <= 40 + 37.5) {

                setScore();
                eaten = true;
                ShowScoreOnScreen();

            }

            if (!eaten && eqDistance(xPosition, yPosition, randomX1, randomY1) <= 30 + 30.5) {

                setScore();
                eaten = true;
                ShowScoreOnScreen();

            }
        }

//bugs bunney player
        handleKeyPress();
        switch (playerDirection) {

            case "up":
                if (playerNum == 1) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 0, 400, 400);
                    gl.glPopMatrix();
                }
                if (playerNum == 2) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 1, 400, 400);
                    gl.glPopMatrix();
                }
                break;

            case "down":
                if (playerNum == 1) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 2, 400, 400);
                    gl.glPopMatrix();
                }
                if (playerNum == 2) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 3, 400, 400);
                    gl.glPopMatrix();
                }
                break;

            case "left":
                if (playerNum == 1) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 4, 400, 400);
                    gl.glPopMatrix();
                }
                if (playerNum == 2) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 5, 400, 400);
                    gl.glPopMatrix();
                }
                break;

            case "right":
                if (playerNum == 1) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 6, 400, 400);
                    gl.glPopMatrix();
                }
                if (playerNum == 2) {
                    gl.glPushMatrix();
                    DrawPlayer(gl, xPosition, yPosition, 7, 400, 400);
                    gl.glPopMatrix();
                }
                break;
        }

        if (!timer.isRunning()) {
//            if (Succeed) {
//                try {
//                    dispose();
//                } catch (Exception ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                     
//                    new Win();
//                } catch (LineUnavailableException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (UnsupportedAudioFileException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                try {
//                    dispose();
//                } catch (Exception ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    new hardLose();
//                } catch (LineUnavailableException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (UnsupportedAudioFileException ex) {
//                    Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

        gl.glDisable(GL.GL_TEXTURE_2D);

    }

    public void countdownTimer() {

        timer = new javax.swing.Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (animator.isAnimating()) {
                    second--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    counterText.setText(ddMinute + ":" + ddSecond);

                    if (second == -1) {
                        second = 59;
                        minute--;
                        ddSecond = dFormat.format(second);
                        ddMinute = dFormat.format(minute);
                        counterText.setText(ddMinute + ":" + ddSecond);
                    }
                    if (minute == 0 && second == 0) {
                        timer.stop();

                    }

                    if (!timer.isRunning()) {
                        if (CheckScore()) {
                            try {
                                dispose();
                            } catch (Exception ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {

                                new Win();
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedAudioFileException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                dispose();
                            } catch (Exception ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                new hardLose();
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedAudioFileException ex) {
                                Logger.getLogger(EsayGLEventListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }
            }
        });
    }

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);	// Turn Blending On

//        gl.glColor3f(0, 0.5f, 0.5f);
        gl.glPushMatrix();
        gl.glScaled(510, 305, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawSprite(GL gl, double x, double y, int index, double width, double height) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScaled(width / 4, height / 4, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawPlayer(GL gl, double x, double y, int index, double width, double height) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texturesP[index]);	// Turn Blending On
        gl.glPushMatrix();

        gl.glTranslated(x, y, 0);
        gl.glScaled(width / 5, height / 5, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public boolean CheckScore() {

        if (score >= 120) {
            Succeed = true;
            return Succeed;
        } else {
            Succeed = false;
            return Succeed;
        }
    }

    public void setScore() {
        score += 1;

    }

    public int getCurrentScore() {
        return score;
    }

    void ShowScoreOnScreen() {

        System.out.println(getCurrentScore());
        ShowCurrentScore.setText("" + getCurrentScore());
    }

    double eqDistance(double x1, double y1, double x2, double y2) {
        //System.out.println(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    void setCanvas(GLCanvas glcanvas) {
        this.glc = glcanvas;
    }

    public void handleKeyPress() {

        if (timer.isRunning()) {
            if ((isKeyPressed(KeyEvent.VK_UP)) || (isKeyPressed(KeyEvent.VK_DOWN)) || (isKeyPressed(KeyEvent.VK_LEFT)) || (isKeyPressed(KeyEvent.VK_RIGHT))) {
                if (isKeyPressed(KeyEvent.VK_UP)) {
                    playerDirection = "up";
                    if (yPosition < yCollision[1]) {
                        yPosition += playerSpeed;
                    }

                }
                if (isKeyPressed(KeyEvent.VK_DOWN)) {
                    playerDirection = "down";
                    if (yPosition > yCollision[0]) {
                        yPosition -= playerSpeed;
                    }

                }
                if (isKeyPressed(KeyEvent.VK_LEFT)) {
                    playerDirection = "left";
                    if (xPosition > xCollision[0]) {
                        xPosition -= playerSpeed;
                    }

                }
                if (isKeyPressed(KeyEvent.VK_RIGHT)) {
                    playerDirection = "right";
                    if (xPosition < xCollision[1]) {
                        xPosition += playerSpeed;
                    }

                }
                playerCount++;

                if (playerCount > 12) {
                    if (playerNum == 1) {
                        playerNum = 2;
                    } else if (playerNum == 2) {
                        playerNum = 1;
                    }
                    playerCount = 0;
                }
            }
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

}
