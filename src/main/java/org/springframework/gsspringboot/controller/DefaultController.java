package org.springframework.gsspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping({"/", "/vinylShop"})
    public String shopMainPage(){
        return "vinylApp/vinylApp";
    }

    @GetMapping("/completeMP")
    public String completeMP(){
        return "completeMP";
    }

    /*@GetMapping("/completeAP")
    public String completeAP() {
        return "completeApp/completeAP";
    }*/

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
