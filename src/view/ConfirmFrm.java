/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.BookingDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BookedSlotService;
import model.Booking;
import model.Client;
import model.SlotService;
import model.User;

/**
 *
 * @author datnvt
 */
public class ConfirmFrm extends JFrame implements ActionListener{
    private User user;
    private Booking booking;
    private JTextField txtId, txtIdCard, txtName, txtAdress, txtTel, txtEmail;
    private JButton btnSave, btnBack;
    private JTable tblResult;
    private ConfirmFrm mainFrm;
    
    public ConfirmFrm(User user, Booking booking) {
        super("Confirm Booking");
        this.user = user;
        this.booking = booking;
        mainFrm = this;
        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel lblHome = new JLabel("Confirm Booking");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
        lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));
        txtId = new JTextField(15);
        txtId.setEditable(false);
        txtIdCard = new JTextField(15);
        txtIdCard.setEditable(false);
        txtName = new JTextField(30);
        txtName.setEditable(false);
        txtAdress = new JTextField(50);
        txtAdress.setEditable(false);
        txtTel = new JTextField(30);
        txtTel.setEditable(false);
        txtEmail = new JTextField(30);
        txtEmail.setEditable(false);
        btnSave = new JButton("Save");
        btnBack = new JButton("Back");
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(7,2));
        content.add(new JLabel("ID:")); 	content.add(txtId);
        content.add(new JLabel("IdCard:")); content.add(txtIdCard);
        content.add(new JLabel("Name:")); 	content.add(txtName);
        content.add(new JLabel("Address:")); content.add(txtAdress);
        content.add(new JLabel("Tel:")); 	content.add(txtTel);
        content.add(new JLabel("Email:")); 	content.add(txtEmail);
        pnMain.add(content);		  
        btnSave.addActionListener(this);
        btnBack.addActionListener(this);
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));		
        tblResult = new JTable();
        JScrollPane scrollPane= new  JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false); 
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
        pn2.add(scrollPane);
        pnMain.add(pn2);
        JPanel pn3 = new JPanel();
        pn3.setLayout(new BoxLayout(pn3,BoxLayout.X_AXIS));
        pn3.add(btnSave);
        pn3.add(btnBack);
        pnMain.add(pn3);	
        this.add(pnMain);
        initForm();
        this.setSize(600,300);				
        this.setLocation(200,10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
    }
    
    private void initForm() {
        Client client = booking.getClient();
        txtId.setText(String.valueOf(client.getId()));
        txtName.setText(client.getName());
        txtIdCard.setText(client.getIdCard());
        txtEmail.setText(client.getEmail());
        txtAdress.setText(client.getAddress());
        txtTel.setText(client.getTel());
        String[] columnNames = {"checkIn", "checkOut", "price", "note", "SlotServiceid"};
        ArrayList<BookedSlotService> listbooked = booking.getBookedSlotService();
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[][] value = new String[listbooked.size()][5];
        for(int i=0; i<listbooked.size(); i++){
                value[i][0] = tf.format(listbooked.get(i).getCheckin());
                value[i][1] = tf.format(listbooked.get(i).getCheckout());
                value[i][2] = String.valueOf(listbooked.get(i).getPrice());
                value[i][3] = listbooked.get(i).getNote();
                value[i][4] = String.valueOf(listbooked.get(i).getSlotService().getId());
        }
        DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //unable to edit cells
               return false;
            }
        };
        tblResult.setModel(tableModel);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnClicked = (JButton)ae.getSource();
        if (btnClicked.equals(btnSave)) {
            BookingDAO bd = new BookingDAO();
            if (bd.addBooking(booking)) {
                JOptionPane.showMessageDialog(this, "Add Booking is succeffully updated!");                   
                (new SellerHomeFrm(user)).setVisible(true);
                this.dispose();
            }            
        } else if (btnClicked.equals(btnBack)) {
            (new SellerHomeFrm(user)).setVisible(true);
            this.dispose();
        }
    }
    
}
