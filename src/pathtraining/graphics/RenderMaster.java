/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import pathtraining.grapheditingtools.Graph;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Stephan
 */
public class RenderMaster {
    private EntitiesRenderer entitiesR;
    private MapRenderer mapR;
    private GraphRenderer graphR;

    
    private boolean running = true;
    
    private Camera cam;
    
    public RenderMaster(Camera cam, Graph g, MapDrawable map) {
        this.cam = cam;
        
        graphR = new GraphRenderer();
        for(Node n : g.getNodes()){
            graphR.addNode(n);
        }
        
        mapR = new MapRenderer(map);
    }

    

    
    
    public GraphRenderer getGraphR() {
        return graphR;
    }

    
    public BufferedImage render() {
        BufferedImage img = new BufferedImage(cam.getWidth(), cam.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        mapR.render(cam, g);
        graphR.render(cam.getxPos(), cam.getyPos(), cam.getWidth(), cam.getHeight(), g);
        g.dispose();
        return img;
    }


    

    
}
