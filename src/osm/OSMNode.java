/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

/**
 *
 * @author Stephan
 */
public class OSMNode {
    private long id;
    private double realLat, realLon;
    
    

    public OSMNode(long id, double latitude, double longitude) {
        this.id = id;
        this.realLat = latitude;
        this.realLon = longitude;
    }
    
    public OSMNode(String line){
        int idStart = line.indexOf("id=")+4;
        int idEnd = line.indexOf("\" ");
        id = new Long(line.substring(idStart, idEnd));
        
        int latStart = line.indexOf("lat=")+5;
        int latEnd= latStart+1;
        while(line.charAt(latEnd)!= '"'){
            latEnd++;
        }
        String lat = line.substring(latStart, latEnd);
        realLat = new Double(lat);
        
        
        
        int longiStart = line.indexOf("lon=")+5;
        int longiEnd= longiStart+1;
        while(line.charAt(longiEnd)!= '"'){
            longiEnd++;
        }
        String longi = line.substring(longiStart, longiEnd);
        realLon = new Double(longi);
        
    }

    
    
    
    public long getId() {
        return id;
    }

    public double getRealLat() {
        return realLat;
    }

    public double getRealLon() {
        return realLon;
    }

    
    


    
    
}
