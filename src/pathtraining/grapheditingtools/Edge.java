/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.grapheditingtools;

import static pathtraining.grapheditingtools.EdgeAdder.FACTOR;

/**
 *
 * @author Stephan
 */
public class Edge {
    private Node start;
    private Node end;
    
    private double weight;

    public Edge(Node start, Node end, double speed) {
        this.start = start;
        this.end = end;
        this.weight = speed;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    public Node getStart() {
        return start;
    }

    protected void recalcDistance() {
        double xDis = end.getxPos() - start.getxPos();
        double yDis = end.getyPos() - start.getyPos();
        double pxlDistance = Math.sqrt(xDis * xDis + yDis * yDis);
        weight = pxlDistance * FACTOR;
    }
}
