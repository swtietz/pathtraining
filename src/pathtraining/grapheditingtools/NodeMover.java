/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.grapheditingtools;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import pathtraining.Map;

/**
 *
 * @author Stephan
 */
public class NodeMover implements MouseMotionListener, MouseListener{

    private Node node;
    private Map map;
    private Graph graph;
    
    public NodeMover(Map map, Graph graph){
        this.map = map;
        this.graph = graph;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if(node != null){
            moveNode(e.getX(),e.getY());
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(node != null){
            moveNode(e.getX(),e.getY());
        }
    }
    
    
    private void moveNode(int xPos, int yPos){
        Point coord = map.clickToCoord(xPos, yPos);
        node.setX(coord.x);
        node.setY(coord.y);
        
        for(Node n : graph.getNodes()){
            for(Edge e : n.getKantenListe()){
                e.recalcDistance();
            }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        node = map.getClosestNodeByCoord(map.clickToCoord(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        node = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
