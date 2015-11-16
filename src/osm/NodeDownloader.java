/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

/**
 *
 * @author Stephan
 */
public class NodeDownloader {

    
    private NodeDB db;
    
    private NodeDB pathNodes;
    
    
    private LinkedList<Street> streets;

    public NodeDownloader() {
        db = new NodeDB();
        pathNodes = new NodeDB();
        streets = new LinkedList<>();
    
    }
    
    public void downloadPaths(){

        //schorndorf    String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=restaurant][bbox=9.4908142,48.7810801,9.5660019,48.8387351]";
        //String add = "http://open.mapquestapi.com/xapi/api/0.6/node[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        //String add = "http://open.mapquestapi.com/xapi/api/0.6/way[highway=*][bbox=10.8439128,48.0755905,10.8877966,48.1179]";
        
        //String add = "http://open.mapquestapi.com/xapi/api/0.6/way[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        
       //String add = "http://www.informationfreeway.org/api/0.6/*[bbox=9.4908142,48.7810801,9.5660019,48.8387351]";
        
        //overpass als alternative 
        String add = "http://www.overpass-api.de/api/xapi?way[bbox=10.8439128,48.0755905,10.8877966,48.1179][highway=*][@meta]";
        
        
        try {
            
            URL url = new URL(add);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                handleInputLine(inputLine, in);
            }
            in.close();
            
        } catch (MalformedURLException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    
    
    public void handleInputLine(String inputLine, BufferedReader in) throws IOException {

        //System.out.println(inputLine);
        if (inputLine.contains("<node")) {
            OSMNode n = addNode(inputLine);
        }
        if (inputLine.contains("<way")) {
            boolean isHighway = false;
            String streetType = "";
            ArrayList<Long> IDs = new ArrayList<>();
            do {
                if (inputLine.contains("<nd ref")) {
                    String[] split = inputLine.split("\"");
                    IDs.add(new Long(split[1]));
                } else if (inputLine.contains("highway")) {
                    isHighway = true;
                    streetType = inputLine.split("\"")[3];
                }
                inputLine = in.readLine();
                //System.out.println(inputLine);
            } while (!inputLine.contains("</way"));

            if (isHighway) {
                GraphNode previous = null;
                for (Long ID : IDs) {
                    GraphNode n = (GraphNode) pathNodes.get(ID);
                    if (n == null) {
                        n = new GraphNode(db.get(ID));
                        pathNodes.insert(n);
                    }
                    if (previous != null) {
                        Street street = new Street(previous, n);
                        street.setType(streetType);
                        previous.addStreet(street);
                        streets.add(street);
                        street = new Street(n, previous, street.getDistance());
                        street.setType(streetType);
                        n.addStreet(street);
                        streets.add(street);
                    }
                    previous = n;
                    //view.addMapMarker(new MapMarkerCircle(new Coordinate(n.getRealLat(), n.getRealLon()), 0.00001));
                }
            }
        }
    }
    
    
    public void downloadRelations(JMapViewer view){

        //Schorndorf:       String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=restaurant][bbox=9.4908142,48.7810801,9.5660019,48.8387351]";
        //Kaufering:        String add = "http://open.mapquestapi.com/xapi/api/0.6/node[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        //richtig: String add = "http://open.mapquestapi.com/xapi/api/0.6/relation[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        String add = "http://open.mapquestapi.com/xapi/api/0.6/relation[bbox=10.8739128,48.0755905,10.8877966,48.0805786]";
        try {
            
            URL url = new URL(add);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                
            }
            in.close();
            
        } catch (MalformedURLException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
  
    public static void searchForKeywordDownload(String key) {
        //String add = "http://api.openstreetmap.org/api/0.6/map?bbox=10.8439128,48.0755905,10.8877966,48.1179";
        String add = "http://www.overpass-api.de/api/xapi?node[bbox=10.8439128,48.0755905,10.8877966,48.1179][@meta]";
        //String add = "http://www.overpass-api.de/api/xapi?node[name=\"Landsberg\"](around:4000)[name=\"Kaufering\"][@meta]";
        try {
            URL url = new URL(add);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(key)) {
                    System.out.println(inputLine);
                }
            }
            in.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void downloadNodes(JMapViewer view) {

//Schorndorf:       String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=restaurant][bbox=9.4908142,48.7810801,9.5660019,48.8387351]";
//Kaufering:        String add = "http://open.mapquestapi.com/xapi/api/0.6/node[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=restaurant][bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        long start = System.currentTimeMillis();
        try {

            URL url = new URL(add);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                if (inputLine.contains("<node")) {
                    OSMNode n = addNode(inputLine);
                    view.addMapMarker(new MapMarkerCircle(new Coordinate(n.getRealLat(), n.getRealLon()), 0.0003));
                }
            }
            in.close();

        } catch (MalformedURLException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        System.out.println("");
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        System.out.println("");
        System.out.println("Total Nodes: " + db.getNodeCounter());


    }


    public NodeDB downloadBuilding(String name){
        
        NodeDB buildingDB = new NodeDB();
        //Schorndorf:       String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=restaurant][bbox=9.4908142,48.7810801,9.5660019,48.8387351]";
        //Kaufering:        String add = "http://open.mapquestapi.com/xapi/api/0.6/node[bbox=10.8439128,48.0755905,10.8877966,48.1005786]";
        
        //String add = "http://open.mapquestapi.com/xapi/api/0.6/node[amenity=bank][bbox=10.8439128,48.0755905,10.8877966,48.1179]";
        String add = "http://www.overpass-api.de/api/xapi?node[bbox=10.8439128,48.0755905,10.8877966,48.1179][amenity="+name+"][@meta]";
        try {
            
            URL url = new URL(add);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("<node")){
                    GraphNode n = new BuildingNode(inputLine,name);
                    buildingDB.insert(n);
                    System.out.println(name+" downloaded");
                }
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Total "+name+"s: " + buildingDB.getNodeCounter());
        return buildingDB;
    }
    
    public void doXMLRequest() {
        try {
            String body = "<osm-script>\n"
                    + "  <query type=\"node\">"
                    + "    <has-kv k=\"name\" v=\"Kaufering\"/>"
                    + "  </query>"
                    + "  <query type=\"node\">"
                    + "    <around radius=\"5000\"/>\n"
                    + "    <has-kv k=\"amenity\" v=\"bank\"/>\n"
                    + "  </query>\n"
                    + "  <print/>\n"
                    + "</osm-script>";
            


            URL url = new URL("http://overpass-api.de/api/interpreter");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            for (String line; (line = reader.readLine()) != null;) {
                if(line.contains("<node")){
                    GraphNode n = new BuildingNode(line,"bank");
                    pathNodes.insert(n);
                    
                }
            }

            writer.close();
            reader.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NodeDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException e){
            Logger.getLogger(NodeDownloader.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    
    
    private OSMNode addNode(String inLine){
        OSMNode n = new OSMNode(inLine);
        db.insert(n);
        return n;
    }

    public NodeDB getAllNodes() {
        return db;
    }

    public NodeDB getPathNodes() {
        return pathNodes;
    }

    public LinkedList<Street> getStreets() {
        return streets;
    }

    
    
}
