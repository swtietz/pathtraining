/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.grapheditingtools;

import java.awt.Color;
import java.util.ArrayList;
import pathtraining.graphics.GraphRenderer;

/**
 *
 * @author Stephan
 */
public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    
    private int nodeCounter = 0;
    
    
    public Graph(){
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        
    }
    
    
    protected Node createNode(int xPos, int yPos){
        Node n = new Node(nodeCounter, xPos, yPos);
        nodes.add(n);
        nodeCounter++;
        return n;
    }
    
    /**
     * Adds a node to the graph, only call this if node was in graph before!!! 
     * To add a new node use createNode!
     * @param xPos
     * @param yPos
     * @param ID
     * @param c 
     */
    protected Node addNode(int xPos, int yPos, int ID, Color c){
        Node n = new Node(ID, xPos, yPos);
        nodeCounter = Math.max(nodeCounter, ID);
        nodeCounter++;
        n.setColor(c);
        nodes.add(n);
        return n;
    }
    
    protected Edge createdEdge(Node start, Node end, double speed){
        Edge e = new Edge(start, end, speed);
        start.addEdge(e);
        return e;
    }
    
    
    /**
     * Returns the node with specified ID. 
     * Durchsucht eine ungeordnete Liste aller Knoten, recht langsam.
     * @param id
     * @return A node. Null if ID is not in use.
     */
    public Node getNode(int id){
        for(Node n : nodes){
            if(n.getId()==id){
                return n;
            }
        }
        return null;
    }
    
    /**
     * Returns the edge between start and end Node.
     * Durchsucht eine ungeordnete Liste aller Kanten, recht langsam.
     * @param start
     * @param end
     * @return Null if no edge is found.
     */
    public Edge getEdge(Node start, Node end){
        for(Edge e : edges){
            if(e.getStart().equals(start)&&e.getEnd().equals(end)){
                return e;
            }
        }
        return null;
    }

    
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
    
    
    
    
}
