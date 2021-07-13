/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ClientDAO;
import dao.ServiceDAO;
import dao.SlotServiceDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class SearchClientFrm extends JFrame implements ActionListener{
    private ArrayList<Client> listClient;
    private JTextField txtName;
    private JButton btnSearch;
    private JButton btnAddClient;
    private JTable tblResult;
    private User user;
    private ArrayList<BookedSlotService> listbooked;
    private SearchClientFrm mainFrm;

    public SearchClientFrm(User user, ArrayList<BookedSlotService> listbooked){
            super("Search client");
            this.user = user;
            mainFrm = this;
            this.listClient = new ArrayList<Client>();
            this.listbooked = listbooked;
            JPanel pnMain = new JPanel();
            pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
            pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
            pnMain.add(Box.createRigidArea(new Dimension(0,10)));

            JLabel lblHome = new JLabel("Search Client");
            lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
            lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
            pnMain.add(lblHome);
            pnMain.add(Box.createRigidArea(new Dimension(0,20)));

            JPanel pnAdd = new JPanel();
            pnAdd.setLayout(new BoxLayout(pnAdd, BoxLayout.Y_AXIS));
            pnAdd.setSize(this.getSize().width-5, 20);
            btnAddClient = new JButton("Add Client");
            btnAddClient.addActionListener(this);
            pnAdd.add(btnAddClient);
            pnMain.add(pnAdd);
            JPanel pn1 = new JPanel();
            pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
            pn1.setSize(this.getSize().width-5, 20);
            pn1.add(new JLabel("Name: "));
            txtName = new JTextField();
            pn1.add(txtName);
            btnSearch = new JButton("Search");
            btnSearch.addActionListener(this);
            pn1.add(btnSearch);
            pnMain.add(pn1);
            pnMain.add(Box.createRigidArea(new Dimension(0,10)));

            JPanel pn2 = new JPanel();
            pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));		
            tblResult = new JTable();
            JScrollPane scrollPane= new  JScrollPane(tblResult);
            tblResult.setFillsViewportHeight(false); 
            scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));

            tblResult.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int column = tblResult.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                    int row = e.getY() / tblResult.getRowHeight(); // get the row of the button

                    // *Checking the row or column is valid or not
                    if (row < tblResult.getRowCount() && row >= 0 && column < tblResult.getColumnCount() && column >= 0) {
                        Booking booking = new Booking();
                        booking.setBookedDate(new Date());
                        booking.setBookedSlotService(listbooked);
                        booking.setClient(listClient.get(row));
                        booking.setCreator(user);
                        booking.setNote("");
                        (new ConfirmFrm(user, booking)).setVisible(true);
                        mainFrm.dispose();
                    }
                }
            });

            pn2.add(scrollPane);
            pnMain.add(pn2);	
            this.add(pnMain);
            this.setSize(600,300);				
            this.setLocation(200,10);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void btnSearchActionPerformed(ActionEvent e) {
        if((txtName.getText() == null)||(txtName.getText().length() == 0))
            return;
        ClientDAO cd = new ClientDAO();
        listClient = cd.searchClient(txtName.getText());
        String[] columnNames = {"Id", "idCard", "Name", "Adress", "Tel", "Email"};
        String[][] value = new String[listClient.size()][6];
        for(int i=0; i<listClient.size(); i++){
                value[i][0] = listClient.get(i).getId() +"";
                value[i][1] = listClient.get(i).getIdCard();
                value[i][2] = listClient.get(i).getName();
                value[i][3] = listClient.get(i).getAddress();
                value[i][4] = listClient.get(i).getTel();
                value[i][5] = listClient.get(i).getEmail();
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
    
    private void btnAddClientActionPerformed(ActionEvent e) {
        (new AddClientFrm(user, listbooked)).setVisible(true);
        mainFrm.dispose();        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnClicked = (JButton)ae.getSource();
        if(btnClicked.equals(btnSearch)){
            this.btnSearchActionPerformed(ae);
        }
        else if (btnClicked.equals(btnAddClient)) {
            this.btnAddClientActionPerformed(ae);
        }
    }
        
}
