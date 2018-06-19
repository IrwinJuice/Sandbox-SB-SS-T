package org.springframework.gsspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Carousel;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.service.CarouselService;
import org.springframework.gsspringboot.service.VinylService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

@Controller
public class VinylController {

    private final String UPLOAD_FOLDER = "src/main/resources/static/images/vinil/imgs_bd_vinil/";
    private final String DB_PATH       = "images/vinil/imgs_bd_vinil/";
    private final String ADMIN_PAGE    = "/administration-page";
    private final String VINYL_PAGE    = "/vinylShop";

    private VinylService    vinylService;
    private CarouselService carouselService;

    @Autowired
    public VinylController(VinylService vinylService,
                           CarouselService carouselService) {
        this.vinylService = vinylService;
        this.carouselService = carouselService;
    }

    @RequestMapping(value = ADMIN_PAGE, method = RequestMethod.GET)
    public ModelAndView showAdminPage(ModelAndView modelAndView,
                                      Vinyl vinyl) {
        modelAndView.addObject("vinyl",
                               vinyl);
        modelAndView.setViewName("admin/administration-page");
        return modelAndView;
    }

    @RequestMapping(value = ADMIN_PAGE, method = RequestMethod.POST)
    public ModelAndView processAddNewVinyl(ModelAndView modelAndView,
                                           @Valid Vinyl vinyl,
                                           /*BindingResult bindingResult,
                                           HttpServletRequest request*/
                                           @RequestParam("file") MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path.toAbsolutePath(), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        vinyl.setImageLink(DB_PATH + file.getOriginalFilename());
        vinylService.saveVinyl(vinyl);
        modelAndView.setViewName("admin/administration-page");
        return modelAndView;
    }
    @GetMapping({"/", VINYL_PAGE})
    public ModelAndView mainPage(ModelAndView modelAndView) {

        List<Carousel> getSliders = carouselService.getAllSlides();
        List<Vinyl>    getVinyls  = vinylService.getAllVinyl();

        modelAndView.addObject("carousel",
                               getSliders);
        modelAndView.addObject("vinyls",
                               getVinyls);
        modelAndView.setViewName("vinylApp/vinylApp");
        return modelAndView;
    }
}

