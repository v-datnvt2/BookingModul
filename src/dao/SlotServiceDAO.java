/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Service;
import model.SlotService;

/**
 *
 * @author datnvt
 */
public class SlotServiceDAO extends DAO{
    public SlotServiceDAO() {
            super();
    }

    public ArrayList<SlotService> searchFreeCalendar(Date checkin, Date checkout, int idService){
            ArrayList<SlotService> result = new ArrayList<SlotService>();
            String sql = "SELECT distinct tblSlotService.*, tblService.* FROM tblSlotService, tblService WHERE tblSlotService.id NOT IN (SELECT tblSlotServiceid FROM tblBookedService WHERE checkout > ? AND checkIn < ?) AND tblSlotService.tblServiceId = ? AND tblService.id = ?";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, sdf.format(checkin));
                    ps.setString(2, sdf.format(checkout));
                    ps.setString(3, String.valueOf(idService));
                    ps.setString(4, String.valueOf(idService));
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                            SlotService rm = new SlotService();
                            rm.setId(rs.getInt("tblSlotService.id"));
                            rm.setName(rs.getString("tblSlotService.name"));
                            rm.setDes(rs.getString("tblSlotService.info"));
                            Service sv = new Service(idService, rs.getString("tblService.name"), rs.getFloat("tblService.price"), rs.getString("tblService.des"), rs.getString("tblService.time"));
                            rm.setService(sv);
                            result.add(rm);
                    }
            }catch(Exception e){
                    e.printStackTrace();
            }	
            return result;
    }    
}
