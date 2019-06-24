package com.alevel.homework44.repository;

import com.alevel.homework44.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
