/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


/**
 *
 * @author Stephan
 */
public class OSMCanvas extends AutoResizeJPanel implements Runnable{
    
    NodeDB<GraphNode> graphNodes;
    JMapViewer camera;
    


    public OSMCanvas(JFrame frame, JMapViewer camera, NodeDB db) {
        super(frame);
        this.graphNodes = db;
        this.camera = camera;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setDoubleBuffered(true);
        
        
        
        new Thread(this).start();
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);


//                    if(n.isInPath){
//                        g.setColor(Color.red);
//                        //g.fillRect(location.x-sprite.getWidth()/2, location.y-sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight());
//                    }else if(n.isOpen){
//                        g.setColor(Color.black);
//                        //g.fillRect(location.x-sprite.getWidth()/2, location.y-sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight());
//                    }else{
            drawMediumDetail(g);

        

        
        
    }

    private void drawMediumDetail(Graphics g) {
        for (OSMNode m : graphNodes.getNodes()) {
            GraphNode n = (GraphNode) m;
            Point location = camera.getMapPosition(n.getRealLat(), n.getRealLon());
            if (location != null) {
                if (n.isCrossing()) {
                    n.draw(g, location.x, location.y);
                }
                drawSurroundingStreets(g, n, location);
            }
        }
    }
    
    

    private void drawHighDetail(Graphics g) {
        for (OSMNode m : graphNodes.getNodes()) {
            GraphNode n = (GraphNode) m;
            Point location = camera.getMapPosition(n.getRealLat(), n.getRealLon());
            if (location != null) {
                n.draw(g, location.x, location.y);
                drawSurroundingStreets(g, n, location);
            }
        }
    }
    
    private void drawSurroundingStreets(Graphics g, GraphNode n, Point location) {
        for (Street street : n.getStreets()) {
            GraphNode k = street.getEnd();
            Point locationNeighbour = camera.getMapPosition(k.getRealLat(), k.getRealLon(), false);
            if (locationNeighbour != null) {
                g.drawLine(location.x, location.y, locationNeighbour.x, locationNeighbour.y);
            }
        }
    }

    

    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(OSMCanvas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
