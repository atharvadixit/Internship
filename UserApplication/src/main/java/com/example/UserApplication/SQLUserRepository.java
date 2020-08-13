package com.example.UserApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLUserRepository extends JpaRepository<SQLUser, String> {

    @Query(value = "Select * from user where city = ?1", nativeQuery = true)
    List<SQLUser> findByCity(String city);
}
