package com.example.UserApplication;

import lombok.Data;
import org.springframework.data.annotation.Id;


public @Data class MongoUser {
    public String name;
    @Id
    public String email;
    public Long contact;
    public Address address;

    public MongoUser()
    {
        this.name="";
        this.email="";
        this.contact=Long.valueOf(0);
        this.address=new Address("","","");
    }

    public MongoUser(String name, String email, Long contact, Address address)
    {
        this.name=name;
        this.email=email;
        this.contact=contact;
        this.address=address;
    }
}
