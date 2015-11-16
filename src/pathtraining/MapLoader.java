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
public class MapLoader {
    
    public MapWrapper loadGraph(String file){
        String JSONString = readFile(file);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(JSONString);
            
            //init nodes
            JSONArray nodeArray = (JSONArray) json.get("nodeArray");
            int w = new Integer(json.get("w").toString());
            int h = new Integer(json.get("h").toString());
            int xOffset = new Integer(json.get("xOffset").toString());
            int yOffset = new Integer(json.get("yOffset").toString());
            System.out.println("xOffset:"+xOffset);
            System.out.println("yOffset:"+yOffset);
            return new MapWrapper(w, h, xOffset, yOffset, file);
            
        } catch (ParseException pe) {
            System.out.println("Parse Exception at position: " + pe.getPosition());
            System.out.println(pe);
        }


        return null;
        
        
        
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
            Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
}
