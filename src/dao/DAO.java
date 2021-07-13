/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author datnvt
 */
public class DAO {
    public static Connection con;
    protected static java.sql.Timestamp convertDate2SqlDatetime(java.util.Date uDate) {
        java.sql.Timestamp sDate = new java.sql.Timestamp(uDate.getTime());
        return sDate;
    }
    public DAO(){
            if(con == null){
                    String dbUrl = "jdbc:mysql://localhost:3306/BeautySystem?autoReconnect=true&useSSL=false";
                    String dbClass = "com.mysql.jdbc.Driver";

                    try {
                            Class.forName(dbClass);
                            con = DriverManager.getConnection (dbUrl, "admin", "p@ssW0rd");
                    }catch(Exception e) {
                            e.printStackTrace();
                    }
            }
    }    
}
