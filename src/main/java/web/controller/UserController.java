package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userMeth(ModelMap modelMap/*,
                           @RequestParam(value = "count",required = false,defaultValue = "5")*/) {
        return "user";
    }
}
