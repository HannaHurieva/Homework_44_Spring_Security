package com.alevel.homework44.service;

import com.alevel.homework44.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll ();

    User findById(Long id);
    User findByUsername(String username);

    User register(User user);

    void delete(long id);
}
