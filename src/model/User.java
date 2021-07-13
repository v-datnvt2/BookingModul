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

public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private String name;
    private String position;
    private boolean status;
    private int group;
    private float salary;
    private float bonus;
	
    public User() {
            super();
    }

    public User(int id, String username, String password, String name, String position, boolean status, int group, float salary, float bonus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.position = position;
        this.status = status;
        this.group = group;
        this.salary = salary;
        this.bonus = bonus;
    }
       
    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getUsername() {
            return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getPosition() {
            return position;
    }

    public void setPosition(String position) {
            this.position = position;
    }	

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }
}
