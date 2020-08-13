package com.example.UserApplication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

    @Query("{ 'address.city' : '?0' }")
    List<MongoUser> findByCity(String city);

}
