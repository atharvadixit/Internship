package com.example.UserApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public @Data class SQLUser {

    @Column(name = "name")
    public String name;
    @Id
    @Column(name = "email")
    public String email;
    @Column(name = "contact")
    public long contact;
    @Column(name = "street")
    public String street;
    @Column(name = "area")
    public String area;
    @Column(name = "city")
    public String city;

}
