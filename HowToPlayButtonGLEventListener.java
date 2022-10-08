
package BunnyGame;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



public class HowToPlayButtonGLEventListener implements GLEventListener, MouseListener{
    
    FPSAnimator animator;
    int animationIndex = 0;
    int maxWidth = 1020;
    int maxHeight = 610;
    double x, y;
    double x1 = 1, y1 = 1;
    double cita1, cita2, cita3;
    double ang1 = 1, ang = 1;
    int dirction = 1;
    double randomX1 = 0, randomY1 = 0, randomX2 = 0, randomY2 = 0, randomX3 = 0, randomY3 = 0;
    double max = 360, min = 0;
    boolean flag, fire = true, moveingR = false;
    
    int xof3Button = -20, yStart = 200, yScore = 100, yHow = 0;
    int xAduido = 440, yAduio = 260;
    int xHome = 340, yHome=255;
    String textureNames[] = {"project//R.png", "project//general-instruction.png"};
    // , "project//b8.png", "project//b9.png","project//b10.png", "project//b2.png", "project//b3.png", "project//b4.png","project//b5.png","project//b7.png"
    
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    private int mouseX, mouseY;
    private float ballX, ballY;
    private boolean moveLeft, moveDown;
    private GLCanvas glc;
    private double slopeX = 0.5, slopeY = 0.5, speed = 5;
    
    
     public void init(GLAutoDrawable gld) {
        mouseX = 5 * maxWidth;
        mouseY = 5 * maxHeight;

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
//gl.glColor3d(1.88, 0.3, 1.88);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture("Assets" + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
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
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glLoadIdentity();
        gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);
    
     }
    
    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL.GL_TEXTURE_2D);
        
        DrawBackground(gl,0, 610 , 305  );
      
        // how to play image
     DrawSprite(gl, -10, -80, 1, 600, 600);
       
  
       
        gl.glDisable(GL.GL_TEXTURE_2D);
    }
     
     public void DrawBackground(GL gl,int index , double width, double height) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

//        gl.glColor3f(0, 0.5f, 0.5f);
        gl.glPushMatrix();
        
       // gl.glScaled(610, 305, 1);
        gl.glScaled(width , height , 1);
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


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }


    public void DrawSprite(GL gl, double x, double y, int index, double width, double height) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScaled(width / 2, height / 2, 1);
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

    @Override
    public void mouseClicked(MouseEvent e) {

//        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();

        mouseX = (int) ((x / width) * maxWidth) - maxWidth / 2;
        mouseY = maxHeight / 2 - ((int) ((y / height) * maxHeight));
//        glc.repaint();
//        System.out.println("pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = 5 * maxWidth;
        mouseY = 5 * maxHeight;
//        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void setCanvas(GLCanvas glcanvas) {
        this.glc = glcanvas;
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_0) {

            if (fire) {
                x1 = 0;
                fire = false;
                cita1 = Math.random() * (360);
                cita2 = Math.random() * (360);
                cita3 = Math.random() * (360);
            }
        } else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            moveingR = true;
            dirction *= -1;
            ang1--;

        } else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if (animator.isAnimating() == false) {
                animator.start();
            }
        }
        //glc.repaint();
    }
    
    
    
}
