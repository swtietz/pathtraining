/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.awt.Point;
import java.awt.image.BufferedImage;
import pathtraining.grapheditingtools.Graph;
import pathtraining.grapheditingtools.Node;
import pathtraining.graphics.Camera;
import pathtraining.graphics.MapDrawable;
import pathtraining.graphics.Sprite;

/**
 *
 * @author Stephan
 */
public class Map {
    private Graph graph;
    private Camera camera;
    private MapDrawable drawable;
    
    public Map(Graph graph, Sprite mapImage, Camera camera) {
        this.graph = graph;
        this.camera = camera;
        drawable = new MapDrawable(mapImage);
    }
    
    
    
    public Point clickToCoord(int xPos, int yPos){
        return new Point(xPos+camera.getxPos(),yPos+camera.getyPos());
    }
    
    /**
     * Bezogen auf linke obere Kartenecke, nicht Bildschirmecke.
     * @param xPos 
     * @param yPos
     * @return 
     */
    public Node getClosestNodeByCoord(int xCoord, int yCoord){
        return getClosestNodeByCoord(new Point(xCoord, yCoord));
    }
    
    /**
     * Bezogen auf linke obere Kartenecke, nicht Bildschirmecke.
     * @param p
     * @return 
     */
    public Node getClosestNodeByCoord(Point p){
        double shortestDistance = Integer.MAX_VALUE;
        Node closest = null;
        for(Node n : graph.getNodes()){
            double distance =p.distance(new Point(n.getxPos(),n.getyPos()));
            if(distance<shortestDistance){
                closest = n;
                shortestDistance = distance;
            }
        }
        return closest;
    }

    
    /**
     * Returns the drawable of this map, containing the background image.
     * @return 
     */
    public MapDrawable getDrawable() {
        return drawable;
    }
    
    
    
}
