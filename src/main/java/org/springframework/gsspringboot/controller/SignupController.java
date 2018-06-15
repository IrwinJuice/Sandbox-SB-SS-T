package org.springframework.gsspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Role;
import org.springframework.gsspringboot.model.User;
import org.springframework.gsspringboot.service.RoleService;
import org.springframework.gsspringboot.service.UserService;
import org.springframework.gsspringboot.utils.EncrytedPasswordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Controller
public class SignupController {
    private              UserService userService;
    private              RoleService roleService;
    private static final String      CHECK_LOGIN    = "/checkLog";
    private static final String      CHECK_EMAIL    = "/checkEmail";
    private static final String      CHECK_PASSWORD = "/checkPassword";
    private static final String      SIGNUP         = "/signup";
    private static final String      VINYL_PAGE     = "redirect:/vinylShop";

    private static final String PARAM_LOGIN    = "login";
    private static final String PARAM_EMAIL    = "email";
    private static final String PARAM_PASSWORD = "password";


    @Autowired
    public SignupController(UserService userService,
                            RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = CHECK_LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    boolean getLogin(@RequestParam(value = PARAM_LOGIN) String login) {

        for (User u : userService.getAllUsers()) {
            if (!(u.getName()
                   .equals(login)) && Pattern.matches("^[a-zA-Z][a-zA-Z0-9-_]{3,16}$",
                                                      login)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = CHECK_EMAIL, method = RequestMethod.POST)
    public @ResponseBody
    boolean getEmail(@RequestParam(value = PARAM_EMAIL) String email) {

        for (User u : userService.getAllUsers()) {
            if (!(u.getEmail()
                   .equals(email))) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = CHECK_PASSWORD, method = RequestMethod.POST)
    public @ResponseBody
    boolean getPassword(@RequestParam(value = PARAM_PASSWORD) String password) {

        return Pattern.matches("(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
                               password);
    }
    @RequestMapping(value = SIGNUP, method = RequestMethod.POST)
    public String singupSubmit(
                                     @RequestParam(PARAM_LOGIN) String login,
                                     @RequestParam(PARAM_EMAIL) String email,
                                     @RequestParam(PARAM_PASSWORD) String password) {

        String encrytePassword = EncrytedPasswordUtils.encrytePassword(password);

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2L));
        userService.saveNewUser(new User(email,
                                         login,
                                         encrytePassword,
                                         roles));

        return VINYL_PAGE;
    }
}

