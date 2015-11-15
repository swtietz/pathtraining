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
public class Node {
    
    private int id;
    private int xPos, yPos;
    private int discoveryT, lowpoint, finishT;
    
    private Node pi;
    
    private int distance;
    
    private ArrayList<Edge> edges;
    
    
    
    private Color c;

    protected Node(int id, int xPos, int yPos) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.edges = new ArrayList<>();
        c = Color.BLACK;
        

    }
    
    protected void addEdge(Edge e){
        edges.add(e);
    }
    
    /**
     * Gibt eine Liste mit allen Kanten die von diesem Knoten wegführen zurück.
     * @return 
     */
    public ArrayList<Edge> getKantenListe(){
        return edges;
    }
    
    /**
     * Gibt die Adjazentliste dieses Knoten zurück. Für informationen über Kantengeschwindigkeiten bitte getEdgeList() oder getEdge(u) verwenden.
     * @return 
     */
    public ArrayList<Node> getAdjazenzListe(){
        ArrayList<Node> adjListe = new ArrayList<>();
        for(Edge e : edges){
            adjListe.add(e.getEnd());
        }
        return adjListe;
    }
    
    
    /**
     * Gibt die Kante die zum Knoten u führt zurück.
     * @param u Der Zielknoten
     * @return null wenn es keine Kante gibt.
     */
    public Edge getEdge(Node u){
        for(Edge e : edges){
            if(e.getEnd().equals(u)){
                return e;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
    
    
    public Color getColor(){
        return c;
    }

    /**
     * Sets the color of this node. Possible colors are: Color.RED, Color.BLACK, Color.GRAY .
     * @param c 
     */
    public void setColor(Color c) {
        this.c = c;
    }
    
    
    protected void setX(int xPos){
        this.xPos = xPos;
    }
    
    protected void setY(int yPos){
        this.yPos = yPos;
    }

    public int getDiscoveryT() {
        return discoveryT;
    }

    public void setDiscoveryT(int discoveryT) {
        this.discoveryT = discoveryT;
    }

    public int getLowpoint() {
        return lowpoint;
    }

    public void setLowpoint(int lowpoint) {
        this.lowpoint = lowpoint;
    }

    public int getFinishT() {
        return finishT;
    }

    public void setFinishT(int finishT) {
        this.finishT = finishT;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getPi() {
        return pi;
    }

    public void setPi(Node pi) {
        this.pi = pi;
    }
    
    
    

}
