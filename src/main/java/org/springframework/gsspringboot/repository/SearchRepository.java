package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.gsspringboot.model.Vinyl;

import java.util.List;

public interface SearchRepository extends JpaRepository<Vinyl, Long> {

    @Query("SELECT v FROM Vinyl v WHERE v.title like ?#{'%' + #request + '%'}")
    List<Vinyl> findByTitle(String request);

    @Query("SELECT v FROM Vinyl v WHERE v.artist like ?#{'%' + #request + '%'}")
    List<Vinyl> findByArtist(String request);
}
