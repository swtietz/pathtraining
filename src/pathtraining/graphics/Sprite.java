package pathtraining.graphics;

import java.awt.Graphics;
import java.awt.image.*;
/**
 * 
 * @author ausfragezeichen
 */
public class Sprite
{
    private BufferedImage image;
    private int xOffset,yOffset;

    /**
     * Constructor for objects of class Sprite
     */
    public Sprite(BufferedImage image)
    {
        this.image = image;
    }

    
    /**
     * Constructor for objects of class Sprite
     */
    public Sprite(BufferedImage image, int xOffset, int yOffset) {
        this.image = image;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    
    public int getWidth(){
        return image.getWidth(null);
    }
    
    public int getHeight(){
        return image.getHeight(null);
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public void draw(Graphics g,int x,int y) {
	g.drawImage(image,x-xOffset,y-yOffset,null);
    }

    
    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
    
    public void setBI(BufferedImage bi){
        this.image = bi;
    }
    
}

