package com.example.bankstatement.repository;


import org.springframework.data.jpa.repository.JpaRepository; 
import com.example.bankstatement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
