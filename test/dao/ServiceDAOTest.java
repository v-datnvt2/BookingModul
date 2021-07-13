/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Service;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author datnvt
 */
public class ServiceDAOTest {
    ServiceDAO sd = new ServiceDAO();

    /**
     * Test of searchService method, of class ServiceDAO.
     */
    @Test
    public void testSearchServiceStandard() {
        ArrayList<Service> listService = sd.searchService();
        Assert.assertNotNull(listService);
        Assert.assertEquals(5, listService.size());
        return;
    }
    
}
