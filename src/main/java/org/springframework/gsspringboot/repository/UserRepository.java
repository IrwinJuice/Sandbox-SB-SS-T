package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
