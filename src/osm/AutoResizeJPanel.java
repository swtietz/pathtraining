/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osm;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class AutoResizeJPanel extends JPanel {

    public AutoResizeJPanel(JFrame frame) {
        
        frame.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                resetBounds(e.getComponent().getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                resetBounds(e.getComponent().getSize());
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resetBounds(e.getComponent().getSize());
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                
            }
        });
    }
    
    
    private void resetBounds(Dimension d){
        this.setBounds(0,0,d.width,d.height);
    }
    
    
}
