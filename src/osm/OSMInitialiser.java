/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import pathtraining.GraphSaver;
import pathtraining.MapSaver;
import pathtraining.grapheditingtools.EdgeAdder;
import pathtraining.grapheditingtools.Node;
import pathtraining.grapheditingtools.NodeAdder;

/**
 *
 * @author Steve
 */
public class OSMInitialiser {

    
        public     int xOffset ;
        public int yOffset ;
        public int w,h;
        
    public void initOSMCanvas(String name, NodeAdder nodeAdder, EdgeAdder edgeAdder, BoundingBox b, boolean downloadTiles) {
        NodeDownloader n = new NodeDownloader();
        n.downloadPaths(b);
        NodeDB<GraphNode> nodes = n.getPathNodes();
        JMapViewer m = new JMapViewer();
        JFrame f = new JFrame();
        f.setVisible(true);
        f.getContentPane().add(m);
        m.setZoom(17);

        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        double minLat = Double.MAX_VALUE;
        double minLon = Double.MAX_VALUE;

        double maxLat = Double.MIN_VALUE;
        double maxLon = Double.MIN_VALUE;
        for (GraphNode node : nodes.getNodes()) {
            Point p = m.getMapPosition(node.getRealLat(), node.getRealLon(), false);
            minX = (long) Math.min(minX, p.x);
            minY = (long) Math.min(minY, p.y);

            minLat = Math.min(minLat, node.getRealLat());
            minLon = Math.min(minLon, node.getRealLon());
            maxLat = Math.max(maxLat, node.getRealLat());
            maxLon = Math.max(maxLon, node.getRealLon());
//System.out.println(p.x+ "   "+p.y);
        }

        HashMap<GraphNode, Node> nodeDict = new HashMap<>();
        for (GraphNode node : nodes.getNodes()) {
            Point p = m.getMapPosition(node.getRealLat(), node.getRealLon(), false);
            node.setX(p.x - (int) minX);
            node.setY(p.y - (int) minY);

            Node newNode = nodeAdder.addNode(node.getX(), node.getY());
            nodeDict.put(node, newNode);
            //System.out.println(node.getX() + "   " + node.getY());
        }

        for (GraphNode node : nodes.getNodes()) {
            for (Street s : node.getStreets()) {
                double mpx = m.getMeterPerPixel();
                Node start = nodeDict.get(s.getStart());
                Node end = nodeDict.get(s.getEnd());
                double dist = Math.sqrt((start.getxPos()-end.getxPos())*(start.getxPos()-end.getxPos())
                        + (start.getyPos()-end.getyPos())*(start.getyPos()-end.getyPos())) * mpx;
                edgeAdder.addNode(nodeDict.get(s.getStart()), nodeDict.get(s.getEnd()), dist);
            }

        }
        if(downloadTiles){
            downloadTiles(name,minLat, maxLat, minLon, maxLon);
        }
        Point minTile = TileDownloader.getTileNumber(minLat, minLon, 17);
        Point maxTile = TileDownloader.getTileNumber(maxLat, maxLon, 17);
        double lat = BoundingBox.tile2lat(maxTile.y, 17);
        double lon = BoundingBox.tile2lon(minTile.x, 17);
        Point mapEdge = m.getMapPosition(lat, lon,false);
        xOffset = mapEdge.x -(int)minX;
        yOffset = mapEdge.y - (int)minY;
        w = Math.abs(minTile.x-maxTile.x);
        h = Math.abs(minTile.y-maxTile.y);
        f.setVisible(false);

        System.out.println("Done creating graph.");

        
       
    }
    
    public void downloadTiles(String name, double minLat, double maxLat, double minLon, double maxLon){
         TileDownloader tileDownloader = new TileDownloader();
        Point minTile = TileDownloader.getTileNumber(minLat, minLon, 17);
        Point maxTile = TileDownloader.getTileNumber(maxLat, maxLon, 17);

        System.out.println(minTile.x + "  " + minTile.y + "      " + maxTile.x + " " + maxTile.y);
        for (int x = minTile.x; x < maxTile.x; x++) {
            for (int y = maxTile.y; y < minTile.y; y++) {

                String link = TileDownloader.getDownloadLink(new Point(x, y), 17);
                try {
                    TileDownloader.saveImage(link, "src/maps/"+name+"_" + (x - minTile.x) + "_" + (y-maxTile.y) + ".png");
                } catch (IOException ex) {
                    Logger.getLogger(OSMInitialiser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
