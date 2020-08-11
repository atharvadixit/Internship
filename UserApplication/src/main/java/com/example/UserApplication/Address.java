package com.example.UserApplication;

import lombok.Data;

public @Data class Address {
    public String street;
    public String area;
    public String city;

    public Address(String street, String area, String city) {
        this.street = street;
        this.area = area;
        this.city = city;
    }
}
