package com.example.UserApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLUserRepository extends JpaRepository<SQLUser, String> {
}
