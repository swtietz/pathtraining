/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining.graphics;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Stephan
 */
public class CameraController {

    private Camera camera;

    public CameraController(JPanel contentPane, Camera cameran) {
        this.camera = cameran;

        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "up");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "down");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "left");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "right");

        contentPane.getActionMap().put("up",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        camera.moveUp();
                    }
                });
        contentPane.getActionMap().put("down",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        camera.moveDown();
                    }
                });
        contentPane.getActionMap().put("left",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        camera.moveLeft();
                    }
                });
        contentPane.getActionMap().put("right",
                new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        camera.moveRight();
                    }
                });

    }

}
