/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.event.MouseEvent;
import pathtraining.InfoPanel;
import pathtraining.Map;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Steve
 */
public class MouseMotionListener implements java.awt.event.MouseMotionListener{

    private Node closestNode;
    private Map map;
    private InfoPanel infoPanel;
    private GraphRenderer renderer;

    public MouseMotionListener(Map map, InfoPanel InfoPanel, GraphRenderer renderer) {
        this.map = map;
        this.infoPanel = InfoPanel;
        this.renderer = renderer;
    }
    
    
    
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Node temp = map.getClosestNodeByCoord(map.clickToCoord(e.getX(), e.getY()));
        if(temp!=null && !temp.equals(closestNode) ){
            closestNode = temp;
            renderer.setHighlighted(closestNode);
            infoPanel.update(closestNode);
        }
    }
    
}
