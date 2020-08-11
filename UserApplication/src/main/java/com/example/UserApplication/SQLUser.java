package com.example.UserApplication;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public @Data class SQLUser {

    @Column(name = "name")
    public String name;
    @Id
    @Column(name = "email")
    public String email;
    @Column(name = "contact")
    public Long contact;
    @Column(name = "street")
    public String street;
    @Column(name = "area")
    public String area;
    @Column(name = "city")
    public String city;

    public SQLUser()
    {
        name="";
        email="default@email";
        contact=Long.valueOf(0);
        street="";
        area="";
        city="";
    }

    public SQLUser(String name, String email, Long contact, String street, String area, String city) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.street = street;
        this.area = area;
        this.city = city;
    }
}
