/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathtraining;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pathtraining.grapheditingtools.Node;

/**
 *
 * @author Steve
 */
public class InfoPanel extends JPanel {
    JLabel id, position, color, discovery, finish, lowpoint;

    public InfoPanel() {
        setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(300, 400));
        this.setBorder(BorderFactory.createTitledBorder("Node Info:"));
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel idLabel = new JLabel("ID:");
        c.gridx = 0;
        c.gridy = 0;
        add(idLabel,c);
        
        id = new JLabel("-");
        c.gridx = 1;
        c.gridy = 0;
        add(id,c);
        
        
        
        JLabel positionLabel = new JLabel("Position (x|y):");
        
        c.gridx = 0;
        c.gridy = 1;
        add(positionLabel,c);
        
        position = new JLabel("-");
        c.gridx = 1;
        add(position,c);
        
        JLabel colorLabel = new JLabel("Color:");
        c.gridx = 0;
        c.gridy = 2;
        add(colorLabel,c);
        
        color = new JLabel("-");
        c.gridx = 1;
        add(color,c);
        
        JLabel discoveryLabel = new JLabel("Discovery Time:");
        c.gridx = 0;
        c.gridy = 3;
        add(discoveryLabel,c);
        
        discovery = new JLabel("-");
        c.gridx = 1;
        add(discovery,c);
        
        JLabel finishLabel = new JLabel("Finishing Time:");
        c.gridx = 0;
        c.gridy = 4;
        add(finishLabel,c);
        
        finish = new JLabel("-");
        c.gridx = 1;
        add(finish,c);
        
        JLabel lowpointLabel = new JLabel("Lowpoint:");
        c.gridx = 0;
        c.gridy = 5;
        add(lowpointLabel,c);
        
        lowpoint = new JLabel("-");
        c.gridx = 1;
        add(lowpoint,c);
    }
    
    
    public void update(Node n ){
        id.setText(n.getId()+"");
        position.setText(n.getxPos()+"|"+n.getyPos());
        color.setText(n.getColor().toString());
        discovery.setText(n.getDiscoveryT()+"");
        finish.setText(n.getFinishT()+"");
        lowpoint.setText(n.getLowpoint()+"");
        this.repaint();
    }
    
    
    
}
