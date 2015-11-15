/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Stephan
 */
public interface Renderer {
    
    
    public void render(int xPosCam, int yPosCam, int widthCam, int heightCam, Graphics2D g);
    
}
