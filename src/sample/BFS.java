package sample;


import pathtraining.PathTraining;
import pathtraining.grapheditingtools.Edge;
import pathtraining.grapheditingtools.Graph;
import pathtraining.grapheditingtools.Node;

import java.awt.*;
import java.util.ArrayList;

public class BFS {


    public BFS(){
        ArrayList<Node>fifolist = new ArrayList<>();

        // Load the bfs sample map and show UI
        PathTraining map = new PathTraining("bfs_sample_map");

        // Get the actual graph data that we want to work on
        Graph graph = map.getGraph();

        // Define a start node for your BFS
        Node start = graph.getNode(535);

        // Convenience method to move the camera to the start node
        map.focusOnNode(start);



        int counter = 0;

        // Initialise start node as first node of the frontier
        start.setColor(Color.green);
        start.setDiscoveryT(counter);

        // Add startnode to queue
        fifolist.add(start);

        // Start the while loop that runs BFS
        while(!fifolist.isEmpty()){
            counter++;

            Node node = fifolist.remove(0);

            // Get all outgoing edges of the node
            ArrayList<Edge> nachbarn = node.getKantenListe();

            for(Edge e : nachbarn){
                Node nachbar = e.getEnd();
                // Colors can be used to encode states, e.g. green is frontier, red is closed

                // Add a node to the list if it is neither green nor red, i.e. it has not been discovered yet.
                if(nachbar.getColor() != Color.green && nachbar.getColor() != Color.red) {
                    fifolist.add(nachbar);
                    nachbar.setColor(Color.green);
                    nachbar.setDiscoveryT(counter);
                }
                try {
                    Thread.sleep(500);
                }catch(InterruptedException ex){

                }
            }
            // After finishing the node set it to red.
            node.setColor(Color.red);

        }


    }




    public static void main(String[] args) {
        new BFS();
    }
}
