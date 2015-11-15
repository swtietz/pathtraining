/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import pathtraining.graphics.Camera;
import pathtraining.graphics.Canvas;

/**
 *
 * @author Stephan
 */
public class Window {
    
    
    private JFrame frame;
    private JPanel canvasPanel;
    
    private JPanel buttonPanel;
    private InfoPanel infoPanel;
    private JPanel optionsPanel;
    private JButton addNodeButton, addEdgeButton, moveNodeButton, saveGraphButton;
    private JCheckBox showDiscoveryTimeBox,showFinishTimeBox,showDistanceTimeBox,showPiBox;
    
    public Window(boolean isEditMode) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        canvasPanel = new JPanel(new BorderLayout());
        contentPane.add(canvasPanel,BorderLayout.CENTER);
        
        
        initButtonPanel();
        if(isEditMode){
            contentPane.add(buttonPanel,BorderLayout.WEST);
        }
        
        initInfoPanel();
        initOptionsPanel();
        JPanel holder = new JPanel(new GridLayout(2, 1,20,20));
        holder.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        holder.add(infoPanel);
        holder.add(optionsPanel);
        contentPane.add(holder,BorderLayout.EAST);
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void initButtonPanel(){
        
        buttonPanel = new JPanel(new GridLayout(10, 1));
        addNodeButton = new JButton("Add Node");
        addEdgeButton = new JButton("Add Edge");
        moveNodeButton = new JButton("Move Node");
        saveGraphButton = new JButton("Save");
        buttonPanel.add(addNodeButton);
        buttonPanel.add(addEdgeButton);
        buttonPanel.add(moveNodeButton);
        buttonPanel.add(saveGraphButton);
        
    }
    
    private void initInfoPanel(){
      
        infoPanel = new InfoPanel();
        
        
    }
    
    private void initOptionsPanel(){
        optionsPanel = new JPanel();
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, javax.swing.BoxLayout.Y_AXIS ));
        showDiscoveryTimeBox = new JCheckBox("Show discovery times");
        showFinishTimeBox = new JCheckBox("Show finish times");
        showDistanceTimeBox = new JCheckBox("Show distances");
        showPiBox = new JCheckBox("Show Pi");
        optionsPanel.add(showDiscoveryTimeBox);
        optionsPanel.add(showFinishTimeBox);
        optionsPanel.add(showDistanceTimeBox);
        
    }
    
    public void setCanvas(Canvas c){
        canvasPanel.add(c, BorderLayout.CENTER);
        canvasPanel.repaint();
        canvasPanel.updateUI();
    }

    public JButton getAddNodeButton() {
        return addNodeButton;
    }

    public JButton getAddEdgeButton() {
        return addEdgeButton;
    }

    public JButton getMoveNodeButton() {
        return moveNodeButton;
    }

    public JButton getSaveGraphButton() {
        return saveGraphButton;
    }
    
    public JPanel getContentPane(){
        return (JPanel) frame.getContentPane();
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getOptionsPanel() {
        return optionsPanel;
    }

    public JCheckBox getShowDiscoveryTimeBox() {
        return showDiscoveryTimeBox;
    }

    public JCheckBox getShowDistanceTimeBox() {
        return showDistanceTimeBox;
    }

    public JCheckBox getShowFinishTimeBox() {
        return showFinishTimeBox;
    }
    
    
    
    
    
    
}
