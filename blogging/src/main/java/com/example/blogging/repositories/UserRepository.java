package com.example.blogging.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.blogging.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
