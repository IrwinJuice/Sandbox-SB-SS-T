package org.springframework.gsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.gsspringboot.model.Carousel;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselRepository extends JpaRepository<Carousel, Long> {}
