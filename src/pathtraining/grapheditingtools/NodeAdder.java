/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.grapheditingtools;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import pathtraining.graphics.Camera;
import pathtraining.graphics.GraphRenderer;

/**
 *
 * @author Stephan
 */
public class NodeAdder implements MouseListener{
    
    private Graph graph;
    private Camera cam;
    private GraphRenderer renderer;
    
    public NodeAdder(Graph graph, GraphRenderer renderer, Camera cam) {
        this.graph = graph;
        this.cam = cam;
        this.renderer = renderer;
    }
    
    
    
    public void addNode(int xPos, int yPos){
        Node n = graph.createNode(xPos, yPos);
        renderer.addNode(n);
    }

    public void addNode(int xPos, int yPos,int ID,  Color c ){
        Node n = graph.addNode(xPos, yPos, ID, c);
        renderer.addNode(n);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        addNode(e.getX()+cam.getxPos(), e.getY()+cam.getyPos());
        System.out.println("clicked");
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
}
