package com.bravo.carrental.user.repository;

import com.bravo.carrental.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByUserRole(Enum userRole);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByEmail(String email);
}
