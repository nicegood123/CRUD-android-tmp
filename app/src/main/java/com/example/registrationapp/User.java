package com.example.registrationapp;

public class User {

    private int id;
    private String name, address, gender, birthdate, contact, email;
    //Constructor
    public User(int id, String name, String address, String gender, String birthdate, String contact, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
        this.contact = contact;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public int getUserID() {
        return id;
    }

    public int getId() {
        return id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id +" "+ name + " " + address + " " + gender + " " + birthdate + " " + contact + " " + email;
    }
}
