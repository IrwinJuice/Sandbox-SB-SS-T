package org.springframework.gsspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    private final String SIGNIN_PAGE = "/signin";


    @GetMapping("/completeMP")
    public String completeMP(){
        return "completeMP";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping(value = SIGNIN_PAGE)
    public ModelAndView signin(ModelAndView modelAndView) {
        modelAndView.setViewName("signin");
        return modelAndView;
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
