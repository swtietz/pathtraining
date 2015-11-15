/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.grapheditingtools;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import pathtraining.Map;
import pathtraining.graphics.Camera;

/**
 *
 * @author Stephan
 */
public class EdgeAdder implements MouseListener{
    
    private Graph graph;
    private Map map;
    
    private Node start;
    
    /**
     * One pxl equals x meter
     */
    public static final double FACTOR = 0.5;
    
    public EdgeAdder(Graph graph, Map map) {
        this.graph = graph;
        this.map = map;
    }
    
    
    
    public void addNode(Node start, Node end, double speed){
        graph.createdEdge(start, end, speed);
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(start == null){
            start = map.getClosestNodeByCoord(map.clickToCoord(e.getX(), e.getY()));
            start.setColor(Color.RED);
        }else{
            Node end = map.getClosestNodeByCoord(map.clickToCoord(e.getX(), e.getY()));
            end.setColor(Color.RED);
            if(end != start && !start.getAdjazenzListe().contains(end)){
                //String speedValue = JOptionPane.showInputDialog(null, "Enter speed for this edge:");
                double xDis = end.getxPos()-start.getxPos();
                double yDis = end.getyPos()-start.getyPos();
                double pxlDistance = Math.sqrt(xDis*xDis+yDis*yDis);
                double distance = pxlDistance*FACTOR;
                graph.createdEdge(start, end, distance);
            }
            end.setColor(Color.BLACK);
            start.setColor(Color.BLACK);
            start = null;
        }
        
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
