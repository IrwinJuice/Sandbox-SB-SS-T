package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Carousel;
import org.springframework.gsspringboot.repository.CarouselRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carouselService")
public class CarouselService {
    private CarouselRepository carouselRepository;

    @Autowired
    public CarouselService(CarouselRepository carouselRepository){
        this.carouselRepository = carouselRepository;
    }
    public void saveSlide(Carousel carousel){
        carouselRepository.save(carousel);
    }

    public List<Carousel> getAllSlides(){
        return carouselRepository.findAll();
    }
}
