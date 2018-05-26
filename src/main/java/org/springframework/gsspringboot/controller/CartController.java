package org.springframework.gsspringboot.controller;

import org.springframework.gsspringboot.model.Vinyl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    private final String CART_PAGE = "/cart";

    @RequestMapping(value = CART_PAGE, method = RequestMethod.GET)
    public ModelAndView showCartPage(ModelAndView modelAndView,
                                     Vinyl vinyl){
        modelAndView.addObject("vinyl", vinyl);
        modelAndView.setViewName("vinylApp/cart");
        return modelAndView;
    }

}
