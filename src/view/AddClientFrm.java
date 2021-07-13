/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClientDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.BookedSlotService;
import model.Booking;
import model.Client;
import model.SlotService;
import model.User;

/**
 *
 * @author datnvt
 */
public class AddClientFrm extends JFrame implements ActionListener{

    private Client client;
    private JTextField txtId, txtIdCard, txtName, txtAdress, txtTel, txtEmail;
    private JButton btnAdd, btnReset;
    private User user;
    private ArrayList<BookedSlotService> listbooked;

    public AddClientFrm(User user, ArrayList<BookedSlotService> listbooked){
            super("Add Client");
            this.user = user;
            this.listbooked = listbooked;
            this.client = new Client();

            JPanel pnMain = new JPanel();
            pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
            pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
            pnMain.add(Box.createRigidArea(new Dimension(0,10)));

            JLabel lblHome = new JLabel("Add Client");
            lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
            lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
            pnMain.add(lblHome);
            pnMain.add(Box.createRigidArea(new Dimension(0,20)));

            txtId = new JTextField(15);
            txtId.setEditable(false);
            txtIdCard = new JTextField(15);
            txtName = new JTextField(30);
            txtAdress = new JTextField(50);
            txtTel = new JTextField(30);
            txtEmail = new JTextField(30);
            btnAdd = new JButton("Add");
            btnReset = new JButton("Reset");

            JPanel content = new JPanel();
            content.setLayout(new GridLayout(7,2));
            content.add(new JLabel("ID:")); 	content.add(txtId);
            content.add(new JLabel("IdCard:")); content.add(txtIdCard);
            content.add(new JLabel("Name:")); 	content.add(txtName);
            content.add(new JLabel("Address:")); content.add(txtAdress);
            content.add(new JLabel("Tel:")); 	content.add(txtTel);
            content.add(new JLabel("Email:")); 	content.add(txtEmail);
            content.add(btnAdd); 	content.add(btnReset);
            pnMain.add(content);		  
            btnAdd.addActionListener(this);
            btnReset.addActionListener(this);
	
            this.setContentPane(pnMain);
            this.setSize(600,300);				
            this.setLocation(200,10);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton btnClicked = (JButton)e.getSource();
            if(btnClicked.equals(btnReset)){
                txtIdCard.setText("");
                txtName.setText("");
                txtAdress.setText("");
                txtTel.setText("");
                txtEmail.setText("");
                return;
            }
            if(btnClicked.equals(btnAdd)){
                client.setIdCard(txtIdCard.getText());
                client.setName(txtName.getText());
                client.setAddress(txtAdress.getText());
                client.setTel(txtTel.getText());
                client.setEmail(txtEmail.getText());

                ClientDAO cd = new ClientDAO();
                if(cd.addClient(client)) {
                    JOptionPane.showMessageDialog(this, "Add new Client is succeffully updated!");
                    Booking booking = new Booking();
                    booking.setBookedDate(new Date());
                    booking.setBookedSlotService(listbooked);
                    booking.setClient(client);
                    booking.setCreator(user);
                    booking.setNote("");                    
                    (new ConfirmFrm(user, booking)).setVisible(true);
                    this.dispose();
                }	
            }
    }
    
}
