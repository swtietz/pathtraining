/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Color;
import java.util.HashMap;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Stephan
 */
public class NodeDrawable {
    
    private HashMap<Color, Sprite> sprites;
    private Node n;

    public NodeDrawable(Node n) {
        sprites = new HashMap<>();
        sprites.put(Color.BLACK, SpriteStore.get().getSprite("/img/black_s.png",10,10));
        sprites.put(Color.RED, SpriteStore.get().getSprite("/img/red_s.png",10,10));
        sprites.put(Color.GRAY, SpriteStore.get().getSprite("/img/grey_s.png",10,10));
        sprites.put(Color.GREEN, SpriteStore.get().getSprite("/img/green_s.png",10,10));
        this.n = n;
    }
    
    public Sprite getSprite(){
        return sprites.get(n.getColor());
    }
    
    public int getX(){
        return n.getxPos();
    }
    
    public int getY(){
        return n.getyPos();
    }

    public Node getNode() {
        return n;
    }
    
    
    
}
