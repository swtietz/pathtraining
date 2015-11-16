package pathtraining.graphics;

import java.awt.image.*;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * e.g. for image path: "/img/creep/ogre 96x bitmaps/looking s0000.bmp"
 * @author ausfragezeichen
 */
public class SpriteStore {

    private static SpriteStore instance = new SpriteStore();
    
    private GraphicsConfiguration gc;
  
    private HashMap<String,Sprite> sprites;

    /**
     * Constructor for objects of class SpriteStore
     */
    private SpriteStore() {
        sprites = new HashMap();
        gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
    }

    public boolean loadSprite(String ref, int xOffset, int yOffset) {
        BufferedImage sourceImage = null;
        System.out.println("ref:"+ref);
        try {
            
            sourceImage = ImageIO.read(getClass().getResource(ref));  
        } catch (IOException ex) {
            System.err.println("could not load image " + ref);
        }
        if (sourceImage == null) {
            return false;
        }
        Sprite sprite = new Sprite(sourceImage, xOffset, yOffset);
        sprites.put(ref, sprite);
        return true;
    }

    private Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            public int markerRGB = color.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public Sprite getSprite(String ref) {
        Sprite sprite = (Sprite) sprites.get(ref);
        if (sprite == null) {
            if (loadSprite(ref,0,0)) {
                return (Sprite) sprites.get(ref);
            }
        }
        return sprite;
    }
    
    public Sprite getSprite(String ref, int xOffset, int yOffset) {
        Sprite sprite = (Sprite) sprites.get(ref);
        if (sprite == null || sprite.getxOffset()!=xOffset || sprite.getyOffset() != yOffset) {
            if (loadSprite(ref,xOffset,yOffset)) {
                return (Sprite) sprites.get(ref);
            }
        }
        return sprite;
    }
    
    
    
    public static SpriteStore get() {
        return instance;
    }
    
}