/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import pathtraining.grapheditingtools.Edge;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Stephan
 */
public class GraphRenderer implements Renderer {

    private boolean isShowDistance;
    private boolean isShowDiscoveryTime;
    private boolean isShowFinishTime;
    private boolean isShowPi;

    
    private ArrayList<NodeDrawable> nodes;
    private Node highlightedNode;

    public GraphRenderer() {
        nodes = new ArrayList<>();
    }

    @Override
    public void render(int xPosCam, int yPosCam, int widthCam, int heightCam, Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw background
        Stroke s = g2d.getStroke();
        g2d.setStroke(new BasicStroke(8));
        g2d.setColor(Color.WHITE);
        drawEdges(xPosCam, yPosCam, widthCam, heightCam, g2d);

        //draw edges
        g2d.setStroke(s);
        g2d.setColor(Color.BLACK);
        drawEdges(xPosCam, yPosCam, widthCam, heightCam, g2d);

        for (NodeDrawable n : nodes) {
            n.getSprite().draw(g2d, n.getX() - xPosCam, n.getY() - yPosCam);
            if (n.getNode().equals(highlightedNode)) {
                //g2d.fillRect(n.getX()-xPosCam, n.getY()-yPosCam, 30, 30);
                SpriteStore.get().getSprite("/img/highlight_s.png", 10, 10).draw(g2d, n.getX() - xPosCam, n.getY() - yPosCam);
            }

            if (isShowDiscoveryTime || isShowDistance || isShowFinishTime) {
                
                //save g2d style
                Color c = g2d.getColor();
                Font f = g2d.getFont();
                
                String print = "";
                if (isShowDiscoveryTime) {
                    print += "d:" + n.getNode().getDiscoveryT()+" ";
                }
                if (isShowFinishTime) {
                    print += "f:" + n.getNode().getFinishT()+" ";
                }
                if (isShowDistance) {
                    print += "dist:" +  n.getNode().getDistance()+" ";
                }
                if (isShowPi) {
                    print += "pi:" +  n.getNode().getPi().getId()+" ";
                }
            
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.PLAIN, 20));
                g2d.fillRect(n.getX() - xPosCam, n.getY() - yPosCam - 20, g2d.getFontMetrics().stringWidth(print), 22);
                g2d.setColor(Color.BLACK);
                g2d.drawString(print, n.getX() - xPosCam, n.getY() - yPosCam);
                g2d.setColor(c);
                g2d.setFont(f);
            }

        }
    }

    
    private void drawEdges(int xPosCam, int yPosCam, int widthCam, int heightCam, Graphics2D g2d){
        Rectangle cam = new Rectangle(xPosCam, yPosCam, widthCam, heightCam);
        for(NodeDrawable n : nodes){
            for(Edge e : n.getNode().getKantenListe()){
                Point start = new Point(e.getStart().getxPos(),e.getStart().getyPos());
                Point end = new Point(e.getEnd().getxPos(),e.getEnd().getyPos());
                if(cam.contains(start)||cam.contains(end)){
                    //verbindungsvektor berechnen
                    double xDis = end.x-start.x;
                    double yDis = end.y-start.y;
                    //länge des verbindungsvektors
                    double dis = Math.sqrt(xDis*xDis+yDis*yDis);
                    
                    double xNorm = xDis/dis;
                    double yNorm = yDis/dis;
                    
                    //vektor drehen und normalisieren //rechtsdrehung
                    double rotXNorm = (yDis*-1)/dis;
                    double rotYNorm = (xDis)/dis;
                    //g2d.drawLine(start.x-xPosCam-(int)(rotXNorm*3), start.y-yPosCam-(int)(rotYNorm*3), end.x-xPosCam, end.y-yPosCam);
                    //g2d.drawLine(start.x-xPosCam+(int)(rotXNorm*3), start.y-yPosCam+(int)(rotYNorm*3), end.x-xPosCam, end.y-yPosCam);
                    int deltaX = 1;
                    int deltaY = 1;
                    if(start.x > end.x){
                        deltaY = -1;
                    }
                    if(start.y < end.y){
                        deltaX = -1;
                    }
                    GeneralPath path = new GeneralPath();
                    path.moveTo(start.x-xPosCam, start.y-yPosCam);
                    path.curveTo(start.x+xDis*1/4+10*deltaX-xPosCam, start.y+yDis*1/4+10*deltaY-yPosCam, start.x+xDis*3/4+10*deltaX-xPosCam, start.y+yDis*3/4+10*deltaY-yPosCam, end.x-xPosCam, end.y-yPosCam);
                    
                    g2d.draw(path); 
                    
                    
                    GeneralPath arrow = new GeneralPath();
                    Point middle = new Point((int)(start.x+xDis/2+5*xNorm-xPosCam+rotXNorm*10), (int)(start.y+yDis/2+5*yNorm-yPosCam+rotYNorm*10));
                    arrow.moveTo(start.x+xDis/2-5*xNorm-xPosCam+rotXNorm*10,start.y+yDis/2-5*yNorm-yPosCam+rotYNorm*10); //start
                    arrow.lineTo(middle.x,middle.y); //head
                    arrow.lineTo(middle.x+rotXNorm*5,middle.y+rotYNorm*5);
                    arrow.lineTo(middle.x+xNorm*10,middle.y+yNorm*10);
                    arrow.lineTo(middle.x-rotXNorm*5,middle.y-rotYNorm*5);
                    arrow.lineTo(middle.x,middle.y);
                    
                    
                    g2d.draw(arrow);
                    
                    g2d.drawString((int)e.getWeight()+"", (int)(start.x+xDis/2-xPosCam+rotXNorm*25-7), (int)(start.y+yDis/2-yPosCam+rotYNorm*25));
                    
                    
                }
            }
        }
    }

    public void addNode(Node node) {
        nodes.add(new NodeDrawable(node));
    }
    
    public void setHighlighted(Node n){
        highlightedNode = n;
    }

    public void setIsShowDiscoveryTime(boolean isShowDiscoveryTime) {
        this.isShowDiscoveryTime = isShowDiscoveryTime;
    }

    public void setIsShowDistance(boolean isShowDistance) {
        this.isShowDistance = isShowDistance;
    }

    public void setIsShowFinishTime(boolean isShowFinishTime) {
        this.isShowFinishTime = isShowFinishTime;
    }

    public void setIsShowPi(boolean isShowPi) {
        this.isShowPi = isShowPi;
    }
    
    
    
    
}
