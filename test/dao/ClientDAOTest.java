/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import model.Client;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author datnvt
 */
public class ClientDAOTest {
    ClientDAO cd = new ClientDAO();

    /**
     * Test of searchClient method, of class ClientDAO.
     */
    @Test
    public void testSearchClientException1() {
        String key = "xxxxxxxx";
        ArrayList<Client> listClient = cd.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(0, listClient.size());
        return;
    }

    @Test
    public void testSearchClientException2() {
        String key = "qreqwr";
        ArrayList<Client> listClient = cd.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(0, listClient.size());
        return;
    }
    
    @Test
    public void testSearchClientStandard1() {
        String key = "a";
        ArrayList<Client> listClient = cd.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(3, listClient.size());
        for (Client client : listClient) {
            Assert.assertTrue(client.getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }    

    @Test
    public void testSearchClientStandard2() {
        String key = "b";
        ArrayList<Client> listClient = cd.searchClient(key);
        Assert.assertNotNull(listClient);
        Assert.assertEquals(1, listClient.size());
        for (Client client : listClient) {
            Assert.assertTrue(client.getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }
    /**
     * Test of addClient method, of class ClientDAO.
     */
    @Test
    public void testAddClient() {
        Connection con = DAO.con;
        try{
            Client client = new Client();
            client.setIdCard("456456");
            client.setName("test");
            client.setAddress("test");
            client.setEmail("test@gmail.com");
            client.setTel("09834052");
            boolean flag = cd.addClient(client);
            Assert.assertNotNull(flag);
            Assert.assertEquals(true, flag);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.setAutoCommit(false);  
                con.rollback();
                con.setAutoCommit(true);               
            }catch(Exception ex){
                    ex.printStackTrace();
            }
        }
        return;
    }
    
}
