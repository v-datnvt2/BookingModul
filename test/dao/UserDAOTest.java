/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author datnvt
 */
public class UserDAOTest {
    UserDAO ud = new UserDAO();

    /**
     * Test of checkLogin method, of class UserDAO.
     */
    @Test
    public void testCheckLoginStandard() {
        String username = "seller";
        String password = "seller";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = ud.checkLogin(user);
        Assert.assertNotNull(flag);
        Assert.assertEquals(true, flag);
        return;
    }
    
    @Test
    public void testCheckLoginException1() {
        String username = "seller";
        String password = "";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = ud.checkLogin(user);
        Assert.assertNotNull(flag);
        Assert.assertEquals(false, flag);
        return;
    }    

    @Test
    public void testCheckLoginException2() {
        String username = "";
        String password = "abc";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = ud.checkLogin(user);
        Assert.assertNotNull(flag);
        Assert.assertEquals(false, flag);
        return;
    }

    @Test
    public void testCheckLoginException3() {
        String username = "";
        String password = "";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = ud.checkLogin(user);
        Assert.assertNotNull(flag);
        Assert.assertEquals(false, flag);
        return;
    }    
    
    @Test
    public void testCheckLoginException4() {
        String username = "usernametest";
        String password = "usernametest";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = ud.checkLogin(user);
        Assert.assertNotNull(flag);
        Assert.assertEquals(false, flag);
        return;
    } 
}
