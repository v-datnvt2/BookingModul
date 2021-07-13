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

/**
 *
 * @author datnvt
 */
public class ServiceDAO extends DAO{

    public ServiceDAO() {
        super();
    }

    public ArrayList<Service> searchService(){
        ArrayList<Service> result = new ArrayList<Service>();
        String sql = "SELECT * FROM tblService";
        try{
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                        Service rm = new Service();
                        rm.setId(rs.getInt("id"));
                        rm.setName(rs.getString("name"));
                        rm.setPrice(rs.getFloat("price"));
                        rm.setDes(rs.getString("des"));
                        rm.setTime(rs.getString("time"));
                        result.add(rm);
                }
        }catch(Exception e){
                e.printStackTrace();
        }	
        return result;
    }    
}
