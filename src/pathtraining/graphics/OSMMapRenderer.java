/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Graphics;

/**
 *
 * @author Steve
 */
public class OSMMapRenderer extends MapRenderer {
    
    
    MapDrawable[][] sprites;
    int xOffset,yOffset;

    public OSMMapRenderer(String name , int w, int h, int xOffset, int yOffset) {
        super(null);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        sprites = new MapDrawable[h][w];
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                sprites[i][j] = new MapDrawable(SpriteStore.get().getSprite("/maps/"+name+"_"+j+"_"+i+".png",-j*256,-i*256));
            }
        }
    }
    
    @Override
    public void render(Camera camera, Graphics g){
        for(int i = 0; i < sprites.length; i++){
            for(int j = 0; j < sprites[0].length; j++){
                sprites[i][j] .getSprite().draw(g, -camera.getxPos()+xOffset, -camera.getyPos()+yOffset);
            }
        
        }
    }
}
