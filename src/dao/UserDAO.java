/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

/**
 *
 * @author datnvt
 */
public class UserDAO extends DAO{
    public UserDAO() {
            super();
    }

    public boolean checkLogin(User user) {
            boolean result = false;
            String sql = "SELECT id, fullName, position FROM tblUser WHERE username = ? AND password = ?";
            try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("fullName"));
                        user.setPosition(rs.getString("position"));
                        result = true;
                    }
            }catch(Exception e) {
                    e.printStackTrace();
            }
            return result;
    }    
}
