/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author datnvt
 */
public class BookedSlotService implements Serializable{
    private int id;
    private Date checkin;
    private Date checkout;
    private float price;
    private String note;
    private int amount;
    private boolean isCheckedIn;
    private SlotService slotService;

    public BookedSlotService() {
        super();
        this.slotService = new SlotService();
    }

    public BookedSlotService(Date checkin, Date checkout, float price, String note, int amount, boolean isCheckedIn, SlotService slotService) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
        this.note = note;
        this.amount = amount;
        this.isCheckedIn = isCheckedIn;
        this.slotService = slotService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getIsCheckedIn() {
        return isCheckedIn;
    }

    public void setIsCheckedIn(boolean isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public SlotService getSlotService() {
        return slotService;
    }

    public void setSlotService(SlotService slotService) {
        this.slotService = slotService;
    }
}
