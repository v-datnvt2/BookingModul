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
import java.util.ArrayList;
import model.Client;

/**
 *
 * @author datnvt
 */
public class ClientDAO extends DAO{

    public ClientDAO() {
        super();
    }
    
    /**
     * search all clients in the tblClient whose name contains the @key
     * @param key
     * @return list of client whose name contains the @key
     */
    public ArrayList<Client> searchClient(String key){
            ArrayList<Client> result = new ArrayList<Client>();
            String sql = "SELECT * FROM tblClient WHERE name LIKE ?";
            try{
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + key + "%");
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                            Client client = new Client();
                            client.setId(rs.getInt("id"));
                            client.setName(rs.getString("name"));
                            client.setIdCard(rs.getString("idCard"));
                            client.setAddress(rs.getString("address"));
                            client.setTel(rs.getString("tel"));
                            client.setEmail(rs.getString("email"));
                            result.add(client);
                    }
            }catch(Exception e){
                    e.printStackTrace();
            }	
            return result;
    }    
    
    /**
     * add a new @client into the DB
     * @param client
     */
    public boolean addClient(Client client){
        String sql = "INSERT INTO tblClient(idCard, name, address, tel, email) VALUES(?,?,?,?,?)";
        try{
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, client.getIdCard());
                ps.setString(2, client.getName());
                ps.setString(3, client.getAddress());
                ps.setString(4, client.getTel());
                ps.setString(5, client.getEmail());

                ps.executeUpdate();

                //get id of the new inserted client
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                        client.setId(generatedKeys.getInt(1));
                }
                return true;
        }catch(Exception e){
                e.printStackTrace();
        }
        return false;
    }    
}
