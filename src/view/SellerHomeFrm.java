/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.User;

/**
 *
 * @author datnvt
 */
public class SellerHomeFrm extends JFrame implements ActionListener{
    private JButton btnCalendarManager;
    private User user;

    public SellerHomeFrm(User user) {
            super("Calendar management");	
            this.user = user;

            JPanel listPane = new JPanel();
            listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

            JPanel lblPane = new JPanel();
            lblPane.setLayout(new BoxLayout(lblPane, BoxLayout.LINE_AXIS));
            lblPane.add(Box.createRigidArea(new Dimension(300, 0)));
            JLabel lblUser = new JLabel("Loged in as: " + user.getName());
            lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);	
            lblPane.add(lblUser);
            listPane.add(lblPane);
            listPane.add(Box.createRigidArea(new Dimension(0,20)));

            JLabel lblHome = new JLabel("Calendar management");
            lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
            lblHome.setFont (lblHome.getFont ().deriveFont (28.0f));
            listPane.add(lblHome);
            listPane.add(Box.createRigidArea(new Dimension(0,20)));

            btnCalendarManager = new JButton("Add calendar");
            btnCalendarManager.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalendarManager.addActionListener(this);
            listPane.add(btnCalendarManager);
            listPane.add(Box.createRigidArea(new Dimension(0,10)));

            this.setSize(600,300);				
            this.setLocation(200,10);
            this.add(listPane, BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if((e.getSource() instanceof JButton)&&(((JButton)e.getSource()).equals(btnCalendarManager))) {
                    (new SearchFreeCalendarFrm(user)).setVisible(true);
                    this.dispose();
            }else {
                    JOptionPane.showMessageDialog(this, "This function is under construction!");
            }
    }
}
