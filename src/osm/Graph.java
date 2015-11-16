/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;


/**
 *
 * @author Stephan
 */
public class Graph {
    
    NodeDB knoten;

    public Graph() {
        knoten = new NodeDB();
    }
    
    
    
    public void addKnoten(GraphNode node){
        if(knoten.get(node.getId())==null){
            knoten.insert(node);
        }
    }
    
    public GraphNode getKnoten(int ID){
        return (GraphNode)knoten.get(ID);
    }
    
    public boolean contains(OSMNode n){
        return knoten.get(n.getId())!= null;
    }
    
    
    
    
}
