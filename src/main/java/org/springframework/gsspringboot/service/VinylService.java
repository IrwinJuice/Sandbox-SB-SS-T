package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.repository.VinylRepository;
import org.springframework.stereotype.Service;


@Service("vinylService")
public class VinylService {

    private VinylRepository vinylRepository;
    /*Если же опустить все умные слова, то полезную функциональность аннотации @Autowired можно описать следующим образом...
    Используя эту аннотацию, не нужно заботиться о том, как лучше всего передать классу или bean'у экземпляр другого bean'a.
    Фреймворк Spring сам найдет нужный bean и подставит
    его значение в свойство, которое отмечено аннотацией @Autowired.*/
    @Autowired
    public VinylService(VinylRepository vinylRepository){
        this.vinylRepository = vinylRepository;
    }

    public void saveVinyl(Vinyl vinyl){
        vinylRepository.save(vinyl);
    }

}
