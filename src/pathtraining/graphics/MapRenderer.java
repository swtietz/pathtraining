/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Graphics;

/**
 *
 * @author Stephan
 */
public class MapRenderer {
    
    private MapDrawable drawable;
    private Camera camera;

    public MapRenderer(MapDrawable drawable) {
        this.drawable = drawable;
    }
    
     
    public void render(Camera camera, Graphics g){
            drawable.getSprite().draw(g, -camera.getxPos(), -camera.getyPos());
    }
    
    
}
