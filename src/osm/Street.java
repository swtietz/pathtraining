/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.awt.Point;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


/**
 *
 * @author Stephan
 */
public class Street {
    private String type;
    
    private GraphNode start,end;
    
    private double speed = 5;

    private int distance;
    
    public Street(GraphNode start, GraphNode end) {
        this.start = start;
        this.end = end;
        calcDistanceInMeters();
    }

    public Street(GraphNode start, GraphNode end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
    
    
    public void calcDistanceInMeters(){
        JMapViewer view = new JMapViewer();
        //JMapViewer view = Game.get().getViewer();
        double meterPerPxl = view.getMeterPerPixel();
        Point startPoint = view.getMapPosition(start.getRealLat(), start.getRealLon(), false);
        Point endPoint = view.getMapPosition(end.getRealLat(), end.getRealLon(), false);
        distance = (int)(startPoint.distance(endPoint)*meterPerPxl);
    }
    
    

    public GraphNode getStart() {
        return start;
    }

    public void setStart(GraphNode start) {
        this.start = start;
    }

    public GraphNode getEnd() {
        return end;
    }

    public void setEnd(GraphNode end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDistance() {
        return distance;
    }

    
    public void setType(String type) {
        this.type = type;
        switch (type) {
            case "residential":
                speed = 30;
                break;
            case "track":
                speed = 5;
                break;
            case "footway":
                speed = 5;
                break;
            case "steps":
                speed = 4;
                break;
            case "tertiary":
                speed = 50;
                break;
            case "service":
                speed = 10;
                break; 
            case "path":
                speed = 10;
                break; 
            case "secondary":
                speed = 50;
                break;
            case "cycleway":
                speed = 14;
                break; 
            case "pedestrian":
                speed = 5;
                break; 
            case "unclassified":
                speed = 30;
                break; 
                
            default: System.err.println("new type:" + type);
        }
    }
    
    
    
    
}
