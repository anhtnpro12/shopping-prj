/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author TNA
 */
public class User {
    private int userID;
    private String name, gender;
    private Date birthDate, createDate;
    private String address;
    private int accountId;

    public User() {
    }

    public User(int userID, String name, String gender, Date birthDate, Date createDate, String address, int accountId) {
        this.userID = userID;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.address = address;
        this.accountId = accountId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", name=" + name + ", gender=" + gender + ", birthDate=" + birthDate + ", createDate=" + createDate + ", address=" + address + ", accountId=" + accountId + '}';
    }
    
    
}
