/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SlotService;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author datnvt
 */
public class SlotServiceDAOTest {
    SlotServiceDAO ssd = new SlotServiceDAO();

    /**
     * Test of searchFreeCalendar method, of class SlotServiceDAO.
     */
    @Test
    public void testSearchFreeCalendarStandard() {
        String checkinTest = "2020-06-28 08:00:00";
        String checkoutTest = "2020-06-28 08:30:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date checkin = sdf.parse(checkinTest);
            Date checkout = sdf.parse(checkoutTest);
            ArrayList<SlotService> listSlot = ssd.searchFreeCalendar(checkin, checkout, 2);
            Assert.assertNotNull(listSlot);
            Assert.assertEquals(2, listSlot.size());
        } catch (ParseException ex) {
            Logger.getLogger(SlotServiceDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }
    
}
