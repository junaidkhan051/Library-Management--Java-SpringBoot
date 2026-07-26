package com.LibraryManagement.LibraryManagement.Repository;

import com.LibraryManagement.LibraryManagement.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<user, Long> {

    Optional<user> findByUsername(String username);

    Optional<user> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}