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
public class Booking implements Serializable{
    private int id;
    private Date bookedDate;
    private String note;
    private Client client;
    private User creator;
    private ArrayList<BookedSlotService> bookedSlotService;

    public Booking() {
        super();
        this.bookedSlotService = new ArrayList<BookedSlotService>();
    }

    public Booking(Date bookedDate, String note, Client client, User creator, ArrayList<BookedSlotService> bookedSlotService) {
        this.bookedDate = bookedDate;
        this.note = note;
        this.client = client;
        this.creator = creator;
        this.bookedSlotService = bookedSlotService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<BookedSlotService> getBookedSlotService() {
        return bookedSlotService;
    }

    public void setBookedSlotService(ArrayList<BookedSlotService> bookedSlotService) {
        this.bookedSlotService = bookedSlotService;
    }
    
}
