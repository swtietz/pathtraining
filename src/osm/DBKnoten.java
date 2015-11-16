/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

/**
 *
 * @author Stephan
 */
public class DBKnoten {
    private OSMNode node;
    private DBKnoten childLeft,childRight;

    public DBKnoten(OSMNode node) {
        this.node = node;
    }

    public OSMNode getNode() {
        return node;
    }
    
    
    
    
    public DBKnoten getChildLeft() {
        return childLeft;
    }

    
    public void setChildLeft(DBKnoten childLeft) {
        this.childLeft = childLeft;
    }

    public DBKnoten getChildRight() {
        return childRight;
    }

    public void setChildRight(DBKnoten childRight) {
        this.childRight = childRight;
    }
    
        
    public boolean isOtherBigger(DBKnoten n){
        return n.getNode().getId()>node.getId();
    }
    

}
