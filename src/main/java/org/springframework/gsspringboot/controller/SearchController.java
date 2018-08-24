package org.springframework.gsspringboot.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    private final String SEARCH_PAGE = "search";
    private final String URL_BASE_SEARCH = "/base-search";

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = URL_BASE_SEARCH, method = RequestMethod.GET)
    public ModelAndView getVinyl(ModelAndView modelAndView,
                                 @RequestParam String data) {

        List<Vinyl> dataList = searchService.findByTitleOrArtist(data);
        System.out.println(dataList);
        modelAndView.addObject("dataList", dataList);
        modelAndView.setViewName(SEARCH_PAGE);
        return modelAndView;
    }
}
