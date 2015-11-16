/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author Stephan
 */
public class MapSaver {
    JSONObject json;

    public MapSaver(String mapname, int w, int h, int xOffset, int yOffset) {
        json = new JSONObject();
       
        json.put("w", w);
        json.put("h", h);
        json.put("name", mapname);
        json.put("xOffset", xOffset);
        json.put("yOffset", yOffset);
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/maps/"+mapname+".map"));
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
