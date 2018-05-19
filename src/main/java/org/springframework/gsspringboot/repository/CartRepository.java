package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {}
