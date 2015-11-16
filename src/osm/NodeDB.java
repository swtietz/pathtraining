/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.util.LinkedList;

/**
 *
 * @author Stephan
 */
public class NodeDB<p extends OSMNode> {
    
    private DBKnoten root;
    private int nodeCounter;
    
    LinkedList<p>nodes = new LinkedList<>();
    
    private double smallestLat, smallestLong;
    private double biggestLat, biggestLong;
    
    
    public void insert(p n){
        nodes.add(n);
        DBKnoten knoten = new DBKnoten(n);
        if(root == null){
           root = knoten;
           smallestLong = n.getRealLon();
           smallestLat = n.getRealLat();
                   
       } else {
           
           DBKnoten current = root;
           boolean done = false;
           while(!done)
           if(knoten.isOtherBigger(current)){
               if(current.getChildLeft() == null){
                   current.setChildLeft(knoten);
                   checkRange(n);
                   done = true;
               }else { 
                   current = current.getChildLeft();
               }
           }else{
               if(current.getChildRight() == null){
                   current.setChildRight(knoten);
                   checkRange(n);
                   done = true;
               }else{
                   current = current.getChildRight();
               }
           }
       }
       nodeCounter++;
           
    }

    

    
    public p get(long ID){
        DBKnoten current = root;
        while(current != null && current.getNode().getId() != ID){
            if(ID<current.getNode().getId()){
                current = current.getChildLeft();
            }else{
                current = current.getChildRight();
            }
        }
        if(current == null){
            return null;
        }
        return (p)current.getNode();
    }
    
    
    
    public int getNodeCounter() {
        return nodeCounter;
    }

    public LinkedList<p> getNodes() {
        return nodes;
    }
    
    
    
    private void checkRange(OSMNode n){
        if(smallestLong > n.getRealLon()){
            smallestLong = n.getRealLon();
        }
        if(smallestLat > n.getRealLat()){
            smallestLat = n.getRealLat();
        }
        if(biggestLat < n.getRealLat()){
            biggestLat = n.getRealLat();
        }
        if(biggestLong < n.getRealLon()){
            biggestLong = n.getRealLon();
        }
    }   
    
}
