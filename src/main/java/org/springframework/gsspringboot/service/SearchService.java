package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.repository.SearchRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {


    private SearchRepository searchRepository;

    @Autowired
    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<Vinyl> findByTitleOrArtist(String request){

        List<Vinyl> titleList = searchRepository.findByTitle(request);
        List<Vinyl> artistList = searchRepository.findByArtist(request);
        List<Vinyl> both = new ArrayList<>();

        both.addAll(titleList);
        both.addAll(artistList);

        return both;
    }
}
