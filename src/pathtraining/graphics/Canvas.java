/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Stephan
 */
public class Canvas extends JPanel{
    
    private boolean running = true;
    private RenderMaster renderer;

    public Canvas(RenderMaster renderer, final Camera camera, MouseMotionListener listener) {
        this.renderer = renderer;
        this.setFocusable(true);
        this.setEnabled(true);
        this.setPreferredSize(new Dimension(1024,768));
        this.addMouseMotionListener(listener);
    }
    
    
    public void draw(Graphics2D g){
        BufferedImage img = renderer.render();
        
        g.drawImage(img, 0, 0, null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        draw(g2);
    }
    
    public void start(){
        new RenderTimer().start();
    }
    
    class RenderTimer extends Thread {

        @Override
        public void run() {
            this.setName("Renderer");
        
            while (running && !isInterrupted()) {
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    this.interrupt();
                }
            }
        }
    }
}
