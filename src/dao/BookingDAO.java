/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import model.BookedSlotService;
import model.Booking;

/**
 *
 * @author datnvt
 */
public class BookingDAO extends DAO{
    public BookingDAO() {
        super();
    }    
    
    public boolean addBooking(Booking booking) {
        String sql_booking = "INSERT INTO tblBooking(note, bookDay, tblClientid, tblUserid) VALUES(?,?,?,?)";
        String sql_bookedSlotService = "INSERT INTO tblBookedService(checkIn, checkout, price, note, isCheckedIn, tblBookingid, tblSlotServiceid, tblUserId) VALUES(?,?,?,?,?,?,?,?)";
        try{
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PreparedStatement ps = con.prepareStatement(sql_booking, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, booking.getNote());
            ps.setTimestamp(2, DAO.convertDate2SqlDatetime(booking.getBookedDate()));
            ps.setInt(3, booking.getClient().getId());
            ps.setInt(4, booking.getCreator().getId());
            ps.executeUpdate();

            //get id of the new inserted client
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                    booking.setId(generatedKeys.getInt(1));
            }
            ps = con.prepareStatement(sql_bookedSlotService, Statement.RETURN_GENERATED_KEYS);
            for (BookedSlotService obj : booking.getBookedSlotService()) {
                ps.setTimestamp(1, DAO.convertDate2SqlDatetime(obj.getCheckin()));
                ps.setTimestamp(2, DAO.convertDate2SqlDatetime(obj.getCheckout()));
                ps.setFloat(3, obj.getPrice());
                ps.setString(4, obj.getNote());
                ps.setInt(5, obj.getIsCheckedIn() ? 1 : 0);
                ps.setInt(6, booking.getId());
                ps.setInt(7, obj.getSlotService().getId());
                ps.setInt(8, booking.getCreator().getId());
                ps.executeUpdate();
                //get id of the new inserted client
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                        obj.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        }catch(Exception e){
                return false;
        }
    }
}
