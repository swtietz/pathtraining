/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.awt.Graphics;
import pathtraining.graphics.SpriteStore;


/**
 *
 * @author Stephan
 */
public class BuildingNode extends GraphNode {

    String type;
    
    public BuildingNode(String inline, String type) {
        super(inline);
        this.type = type;
        sprite = SpriteStore.get().getSprite("/img/bank.png");
        
    }
    
    @Override
    public void draw(Graphics g, int x, int y){
        super.draw(g, x, y);
        //System.out.println("bank drawn at" +x+"  "+y);
    }
    
    
}
