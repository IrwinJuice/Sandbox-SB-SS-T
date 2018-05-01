package org.springframework.gsspringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
