/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.awt.Color;
import java.awt.Transparency;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pathtraining.grapheditingtools.Edge;
import pathtraining.grapheditingtools.Graph;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Stephan
 */
public class GraphSaver {
    JSONObject json;

    public GraphSaver(Graph graph) {
        json = new JSONObject();
       
        JSONArray nodes = new JSONArray();

        for (Node n : graph.getNodes()) {


            String color = "";
            if (n.getColor().equals(Color.BLACK)) {
                color = "black";
            } else if (n.getColor().equals(Color.RED)) {
                color = "red";
            }
            JSONObject nodeJSON = new JSONObject();
            nodeJSON.put("xPos", "" + n.getxPos());
            nodeJSON.put("yPos", "" + n.getyPos());
            nodeJSON.put("ID", "" + n.getId());
            nodeJSON.put("color", color);
            nodes.add(nodeJSON);

        }

        json.put("nodeArray", nodes);

        
        JSONArray edges = new JSONArray();
        
        for(Node n : graph.getNodes()){
            for(Edge e : n.getKantenListe()){
                JSONObject edgeJSON = new JSONObject();
                edgeJSON.put("start", e.getStart().getId()+"");
                edgeJSON.put("end", e.getEnd().getId()+"");
                edgeJSON.put("weight", e.getWeight()+"");
                edges.add(edgeJSON);
            }
        }
        
        json.put("edgeArray", edges);
        
        
        System.out.println(json.toJSONString());


        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("campus.graph"));
            writer.write(json.toJSONString());

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }


    }

}
