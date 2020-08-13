package com.example.UserApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data class MongoUser {
    public String name;
    @Id
    public String email;
    public Long contact;
    public Address address;
}
