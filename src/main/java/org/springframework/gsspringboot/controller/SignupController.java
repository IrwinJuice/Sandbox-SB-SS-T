package org.springframework.gsspringboot.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Controller
@Order(200)
public class SignupController {


    private              UserService userService;
    private              RoleService roleService;

    private static final String      SIGNUP         = "/signup";
    private static final String PARAM_LOGIN    = "login";
    private static final String PARAM_EMAIL    = "email";
    private static final String PARAM_PASSWORD = "password";

    @Autowired
    public SignupController(UserService userService,
                            RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = SIGNUP, method = RequestMethod.POST)
    public @ResponseBody
    String singupSubmit(@RequestParam(PARAM_LOGIN) String login,
                        @RequestParam(PARAM_EMAIL) String email,
                        @RequestParam(PARAM_PASSWORD) String password) {

        JSONObject jsonObject = new JSONObject();

        boolean passwordTrue = Pattern.matches("(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
                        password);
        boolean loginTrue = Pattern.matches("^[a-zA-Z][a-zA-Z0-9-_]{3,16}$",
                                               login);
        boolean emailTrue = Pattern.matches("^[a-zA-Z][a-zA-Z0-9-_][@]{3,}$",
                                               login);

        if(!(loginTrue) && !(emailTrue) && !(passwordTrue)){
            jsonObject.put("errorNull", false);
            return jsonObject.toString();
        }

        User findByName = userService.findByName(login);
        User findByEmail = userService.findByEmail(email);

        if(findByName != null){
            jsonObject.put("errorLogin", false);
        }
        if (findByEmail != null) {
            jsonObject.put("errorEmail", false);
        }
        if (findByName == null & findByEmail == null ){
            String    encrytePassword = EncrytedPasswordUtils.encrytePassword(password);
            Set<Role> roles           = new HashSet<>();
            roles.add(roleService.getRoleById(2L));
            userService.saveNewUser(new User(email,
                                             login,
                                             encrytePassword,
                                             roles));

            jsonObject.put("save", true);
        }
        return jsonObject.toString();
    }

}


