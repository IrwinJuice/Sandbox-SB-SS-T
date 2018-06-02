package org.springframework.gsspringboot.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.gsspringboot.service.VinylService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {
    private final        String URL_CART_PAGE  = "/cart";
    private static final String URL_GET_VINYLS = "/get_vinyls";

    private VinylService vinylService;

    @Autowired
    public CartController(VinylService vinylService) {
        this.vinylService = vinylService;
    }


    @RequestMapping(value = URL_CART_PAGE, method = RequestMethod.GET)
    public ModelAndView showCartPage(ModelAndView modelAndView) {
        List<Vinyl> getVinyls = vinylService.getAllVinyl();

        modelAndView.addObject("vinyls",
                               getVinyls);
        modelAndView.setViewName("vinylApp/cart");
        return modelAndView;
    }

    @RequestMapping(value = URL_GET_VINYLS, method = RequestMethod.GET)
    public @ResponseBody
    String getVinyl() {
        List<Vinyl> vinyls = vinylService.getAllVinyl();
        System.out.println(vinyls);
        JSONArray array = new JSONArray(vinyls);
        return array.toString();
    }

}
