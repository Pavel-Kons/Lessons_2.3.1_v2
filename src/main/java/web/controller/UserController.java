package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @RequestMapping(value = "/users"/*, method = RequestMethod.GET*/)
    @GetMapping(value = "/users"/*, method = RequestMethod.GET*/)
    public String printUser(ModelMap model,
                            @RequestParam(value = "count", required = false, defaultValue = "5") Integer count) {
        model.addAttribute("users", userService.getUsers(count));
        User user = new User("A", "B", (byte) 1);
        User user1 = new User("Pavel", "Konstantinov", (byte) 23);
        User user2 = new User("Stepa", "C", (byte) 13);
        User user3 = new User("Masha", "D", (byte) 33);
        User user4 = new User("Katya", "E", (byte) 43);

//        userService.saveUser(user);
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
//        userService.saveUser(user4);
//        userService.deleteUser(1L);
//        userService.updateUser(user, 2L);
//        userService.updateUser(user, 3L);
//        userService.updateUser(user, 4L);
//        userService.updateUser(user, 5L);

        return "users";
    }
}
