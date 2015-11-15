/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pathtraining.grapheditingtools.EdgeAdder;
import pathtraining.grapheditingtools.Graph;
import pathtraining.grapheditingtools.Node;
import pathtraining.grapheditingtools.NodeAdder;

/**
 *
 * @author Stephan
 */
public class GraphLoader {
    
    public void loadGraph(String file, Graph graph, NodeAdder nodeAdder, EdgeAdder edgeAdder){
        String JSONString = readFile(file);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(JSONString);
            
            //init nodes
            JSONArray nodeArray = (JSONArray) json.get("nodeArray");
            for (int i = 0; i < nodeArray.size(); i++) {
                JSONObject nodeJSON = (JSONObject) nodeArray.get(i);

                Color color = Color.BLACK;
                if (nodeJSON.get("color").equals("black")) {
                    color = Color.BLACK;
                } else if (nodeJSON.get("color").equals("red")) {
                    color = Color.RED;
                }
                nodeAdder.addNode(new Integer((String) nodeJSON.get("xPos")), new Integer((String) nodeJSON.get("yPos")), new Integer((String) nodeJSON.get("ID")), color);
            }
            
            //connect nodes
            JSONArray edgeArray = (JSONArray) json.get("edgeArray");
            for (int i = 0; i < edgeArray.size(); i++) {
                JSONObject edgeJSON = (JSONObject) edgeArray.get(i);
                Node start = graph.getNode( new Integer((String) edgeJSON.get("start")));
                Node end = graph.getNode( new Integer((String) edgeJSON.get("end")));
                double weight = new Double((String) edgeJSON.get("weight"));
                edgeAdder.addNode(start, end, weight);   
            }
            
            
        } catch (ParseException pe) {
            System.out.println("Parse Exception at position: " + pe.getPosition());
            System.out.println(pe);
        }



        
        
        
    }
    
    
    private String readFile(String fileAdr){
        try{
            //BufferedReader br = new BufferedReader(new FileReader(fileAdr));
            InputStream input = getClass().getResourceAsStream(fileAdr);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    System.out.println(line);
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            } finally {
                br.close();
            }
        }catch (IOException ex) {
            Logger.getLogger(GraphLoader.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
}
