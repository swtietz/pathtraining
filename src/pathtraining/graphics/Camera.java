/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Dimension;

/**
 *
 * @author Stephan
 */
public class Camera {
    
   /**
    * Topleft corner of camera
    */
    private int xPos, yPos;
    
    private int width, height;

    public Camera(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    public void moveUp(){
        yPos-=10;
    }
    
    public void moveDown(){
        yPos+=10;
    }
    
    public void moveLeft(){
        xPos-=10;
    }
    
    public void moveRight(){
        xPos+=10;
    }
    
    public void setSize(Dimension dim){
        width = dim.width;
        height = dim.height;
        System.out.println("cam resized");
    }
    
}
