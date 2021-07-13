/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BookedSlotService;
import model.Service;
import model.SlotService;
import model.User;

/**
 *
 * @author datnvt
 */
public class SearchFreeCalendarFrm extends JFrame implements ActionListener{
    private ArrayList<SlotService> listSlotService;
    private ArrayList<Service> listServices;
    private ArrayList<BookedSlotService> listbooked;
    private JTextField txtDate;
    private int Serviceid;
    private JComboBox cbxService;
    private JButton btnSearch;
    private JTable tblResult;
    private User user;
    private SearchFreeCalendarFrm mainFrm;
    private Date checkin;
    private Date checkout;

    public SearchFreeCalendarFrm(User user){
            super("Search Free Calendar");
            this.user = user;
            mainFrm = this;
            listSlotService = new ArrayList<SlotService>();
            listServices = new ArrayList<Service>();
            listbooked = new ArrayList<BookedSlotService>();
            checkin = null;
            checkout = null;
            Serviceid = 2;
            JPanel pnMain = new JPanel();
            pnMain.setSize(this.getSize().width-5, this.getSize().height-20);		
            pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
            pnMain.add(Box.createRigidArea(new Dimension(0,10)));

            JLabel lblHome = new JLabel("Search a slot service to book");
            lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);	
            lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
            pnMain.add(lblHome);
            pnMain.add(Box.createRigidArea(new Dimension(0,20)));

            JPanel pn1 = new JPanel();
            pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
            pn1.setSize(this.getSize().width-5, 20);
            pn1.add(new JLabel("Date: "));
            txtDate = new JTextField();
            pn1.add(txtDate);
            pn1.add(new JLabel("Service: "));
            ServiceDAO sd = new ServiceDAO();
            listServices = sd.searchService();
            String[] labels = new String[listServices.size() - 1];
            for (int i = 1; i < listServices.size(); i++) {
                labels[i - 1] = listServices.get(i).getName();
            }
            cbxService = new JComboBox(labels);
            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    int state = ie.getStateChange();
                    int row = cbxService.getSelectedIndex();
                    Serviceid = row + 2;
                }
            };
            cbxService.addItemListener(itemListener);
            pn1.add(cbxService);
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
                        BookedSlotService book = new BookedSlotService();
                        book.setCheckin(checkin);
                        book.setCheckout(checkout);
                        book.setIsCheckedIn(false);
                        book.setSlotService(listSlotService.get(row));
                        book.setNote("");
                        book.setAmount(1);
                        book.setPrice(book.getSlotService().getService().getPrice());
                        listbooked.add(book);
                        (new SearchClientFrm(user, listbooked)).setVisible(true);
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

    private String computeCheckout(String checkin, String time) {
        String[] splitCheckin = checkin.split("\\s");
        String[] hms1 = splitCheckin[1].split(":");
        String[] hms2 = time.split(":");
        int tmp = 0;
        String dateStr = "";
        for (int i = hms1.length - 1; i >= 0; i--) {
            int sum = Integer.parseInt(hms1[i]) + Integer.parseInt(hms2[i]) + tmp;
            tmp = 0;
            if (sum >= 60) {
                sum -= 60;
                tmp = 1;
            } 
            if (sum >= 10) {
                dateStr = String.valueOf(sum) + dateStr;
            }else {
                dateStr = "0" + String.valueOf(sum) + dateStr;
            }
            if (i != 0) {
                dateStr = ":" + dateStr;
            }
        }
        String checkout = splitCheckin[0] + " " + dateStr;
        return checkout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton btnClicked = (JButton)e.getSource();
            if(btnClicked.equals(btnSearch)){
                if((txtDate.getText() == null)||(txtDate.getText().length() == 0))
                        return;
                SlotServiceDAO ssd = new SlotServiceDAO();
                try {
                    String time1 = txtDate.getText().trim();
                    String timeService = listServices.get(Serviceid - 1).getTime();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    checkin = sf.parse(time1);
                    checkout = sf.parse(this.computeCheckout(time1, timeService));
                    listSlotService = ssd.searchFreeCalendar(checkin, checkout, Serviceid);
                } catch (ParseException ex) {
                    System.out.println("Fail Search Free Calendar");
                }
                String[] columnNames = {"Id", "Name", "Description", "ServiceId"};
                String[][] value = new String[listSlotService.size()][4];
                for(int i=0; i<listSlotService.size(); i++){
                        value[i][0] = listSlotService.get(i).getId() +"";
                        value[i][1] = listSlotService.get(i).getName();
                        value[i][2] = listSlotService.get(i).getDes();
                        value[i][3] = listSlotService.get(i).getService().getId() + "";
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
    }
}
