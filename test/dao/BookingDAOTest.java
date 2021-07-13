/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import model.BookedSlotService;
import model.Booking;
import model.Client;
import model.Service;
import model.SlotService;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author datnvt
 */
public class BookingDAOTest {
    BookingDAO bd = new BookingDAO();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Test of addBooking method, of class BookingDAO.
     */
    @Test
    public void testAddBookingStandard() {
        Connection con = DAO.con;
        try {
            Booking booking = new Booking();
            User user = new User();
            user.setId(2);
            Service service = new Service(2, "Gội đầu", 99000, "không", "00:30:00");
            SlotService slotService = new SlotService(2, "bàn 2", "ca sáng", service);
            Client client = new Client("Nguyen Duc Hanh", "3645634", "Thai Nguyen", "097239223", "hnd@gmail.com");
            client.setId(7);
            booking.setBookedDate(new Date());
            booking.setNote("");
            booking.setCreator(user);
            booking.setClient(client);
            try {
                BookedSlotService booked = new BookedSlotService(sf.parse("2020-07-03 08:00:00"), sf.parse("2020-07-03 08:30:00"), service.getPrice(), "", 1, false, slotService);
                ArrayList<BookedSlotService> listbooked = new ArrayList<BookedSlotService>();
                listbooked.add(booked);
                booking.setBookedSlotService(listbooked);
            } catch (ParseException ex) {
                Logger.getLogger(BookingDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean flag = bd.addBooking(booking);
            Assert.assertNotNull(flag);
            Assert.assertEquals(true, flag);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.setAutoCommit(false);
                con.rollback();
//                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }

    @Test
    public void testAddBookingException1() {
        Connection con = DAO.con;
        try {
            Booking booking = new Booking();
            User user = new User();
            user.setId(2);
            Service service = new Service(2, "Gội đầu", 99000, "không", "00:30:00");
            SlotService slotService = new SlotService(2, "bàn 2", "ca sáng", service);
            Client client = new Client("user test fail", "3645634", "Thai Nguyen", "097239223", "hnd@gmail.com");
            client.setId(1000);
            booking.setBookedDate(new Date());
            booking.setNote("");
            booking.setCreator(user);
            booking.setClient(client);
            try {
                BookedSlotService booked = new BookedSlotService(sf.parse("2020-07-02 08:00:00"), sf.parse("2020-07-02 08:30:00"), service.getPrice(), "", 1, false, slotService);
                ArrayList<BookedSlotService> listbooked = new ArrayList<BookedSlotService>();
                listbooked.add(booked);
                booking.setBookedSlotService(listbooked);
            } catch (ParseException ex) {
                Logger.getLogger(BookingDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean flag = bd.addBooking(booking);
            Assert.assertNotNull(flag);
            Assert.assertEquals(false, flag);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.setAutoCommit(false);
                con.rollback();
//                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }

    @Test
    public void testAddBookingException2() {
        Connection con = DAO.con;
        try {
            Booking booking = new Booking();
            User user = new User();
            user.setId(2);
            Service service = new Service(2, "Gội đầu", 99000, "không", "00:30:00");
            SlotService slotService = new SlotService(9, "test service fail", "ca sáng", service);
            Client client = new Client("Nguyen Duc Hanh", "3645634", "Thai Nguyen", "097239223", "hnd@gmail.com");
            booking.setBookedDate(new Date());
            booking.setNote("");
            booking.setCreator(user);
            booking.setClient(client);
            try {
                BookedSlotService booked = new BookedSlotService(sf.parse("2020-07-02 08:00:00"), sf.parse("2020-07-02 08:30:00"), service.getPrice(), "", 1, false, slotService);
                ArrayList<BookedSlotService> listbooked = new ArrayList<BookedSlotService>();
                listbooked.add(booked);
                booking.setBookedSlotService(listbooked);
            } catch (ParseException ex) {
                Logger.getLogger(BookingDAOTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean flag = bd.addBooking(booking);
            Assert.assertNotNull(flag);
            Assert.assertEquals(false, flag);            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.setAutoCommit(false);
                con.rollback();
//                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }    
}
