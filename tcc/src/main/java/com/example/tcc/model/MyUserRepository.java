package com.example.tcc.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findUserByEmail(String email);
    Optional<MyUser> findUserByUsername(String username);
}
