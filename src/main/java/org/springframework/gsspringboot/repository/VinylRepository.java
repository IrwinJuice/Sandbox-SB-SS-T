package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {

}
