package com.example.SpringApp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<FormBean, String> {
}