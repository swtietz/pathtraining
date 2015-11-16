/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;


import java.awt.Graphics;
import java.util.LinkedList;
import pathtraining.graphics.Sprite;
import pathtraining.graphics.SpriteStore;


/**
 *
 * @author Stephan
 */
public class GraphNode extends OSMNode {
    private LinkedList<Street> neighbours;
    
    
    private int x,y;
    
    public boolean isInPath, isOpen;
    
    protected Sprite sprite;
    
    private boolean isEscapePoint;
    
    public GraphNode(OSMNode n){
        super(n.getId(),n.getRealLat(),n.getRealLon());
        neighbours = new LinkedList<>();
        sprite = SpriteStore.get().getSprite("/img/BLACK.png");
    }
    
    public GraphNode(long id, double x, double y){
        super(id, x, y);
        neighbours = new LinkedList<>();
        sprite = SpriteStore.get().getSprite("/img/BLACK.png");
    }
    
    public GraphNode(String inline) {
        super(inline);
        neighbours = new LinkedList<>();
        sprite = SpriteStore.get().getSprite("/img/BLACK.png");
    }

    
    public void draw(Graphics g , int x, int y){
        if (isCrossing()) {
            g.drawImage(sprite.getImage(), x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, null);
        } else {
            g.drawImage(sprite.getImage(), x - sprite.getWidth() / 4, y - sprite.getHeight() / 4, sprite.getWidth() / 2, sprite.getHeight() / 2, null);
        }
    }
    
    public void addStreet(Street street){
        if(!neighbours.contains(street)){
            neighbours.add(street);
        }
    }

    public LinkedList<Street> getStreets() {
        return neighbours;
    }
    
    
    public Street getStreetTo(GraphNode n){
        for(Street s : neighbours){
            if(s.getEnd().getId() == n.getId()){
                return s;
            }      
        }
        return null;
    }
    
    public boolean isCrossing(){
        return neighbours.size() == 1 || neighbours.size()>2;
    }

    public boolean isExitPoint(){
        return isEscapePoint;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    
}
