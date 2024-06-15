package com.bravo.carrental.auth.api.repository;

import com.bravo.carrental.auth.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long aLong);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByName(String name);
}
