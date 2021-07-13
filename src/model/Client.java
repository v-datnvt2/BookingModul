/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author datnvt
 */
import java.io.Serializable;

public class Client implements Serializable{
    private int id;
    private String name;
    private String idCard;
    private String address;
    private String tel;
    private String email;
	
    public Client() {
        super();
    }
	
    public Client(String name, String idCard, String address, String tel, String email) {
	super();
	this.name = name;
	this.idCard = idCard;
	this.address = address;
	this.tel = tel;
	this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }	
}
