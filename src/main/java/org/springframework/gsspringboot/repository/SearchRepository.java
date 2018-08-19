package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.Vinyl;

import java.util.List;

public interface SearchRepository extends JpaRepository<Vinyl, Long> {

    List<Vinyl> findByTitleOrArtist(String request, String request2);

}
