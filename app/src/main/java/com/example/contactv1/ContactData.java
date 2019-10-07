package com.example.contactv1;

import java.io.Serializable;

public class ContactData implements Serializable {
    private int id;
    private String contactName;
    private String avatarName="person_profile";
    private String phoneNumber;
    public ContactData(){
        super();
    }
    public ContactData(String avatarName, String contactName, String phoneNumber) {
        super();
        this.avatarName = avatarName;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }
    public int getID() {

        return this.id;
    }
    public void setId(int id) {

        this.id=id;
    }
    public String getAvatar() {

        return avatarName;
    }
    public void setAvatarName(String avatarName) {

        this.avatarName = avatarName;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {

        this.contactName = contactName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


}
