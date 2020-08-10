package com.example.SpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    private String name;
    @Id
    private String email;
    private long contact;
    Address address;

    public User(String name, String email, long contact, Address address) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address=address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getContact() {
        return contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "FormBean{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                '}';
    }
}
