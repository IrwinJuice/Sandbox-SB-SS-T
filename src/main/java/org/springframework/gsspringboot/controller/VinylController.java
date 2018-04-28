package org.springframework.gsspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.service.VinylService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class VinylController {

    private VinylService vinylService;

    @Autowired
    public VinylController(VinylService vinylService) {
        this.vinylService = vinylService;
    }

    @RequestMapping(value = "/completeAP", method = RequestMethod.GET)
    public ModelAndView showAdminPage (ModelAndView modelAndView, Vinyl vinyl){
        modelAndView.addObject("vinyl", vinyl);
        modelAndView.setViewName("completeApp/completeAP");
        return modelAndView;
    }
    @RequestMapping(value = "/completeAP", method = RequestMethod.POST)
    public ModelAndView processAddNewVinyl(ModelAndView modelAndView,
                                           @Valid Vinyl vinyl,
                                           /*BindingResult bindingResult,*/
                                           HttpServletRequest request){
        vinylService.saveVinyl(vinyl);
        modelAndView.setViewName("completeApp/completeAP");
        return modelAndView;
    }

}
