package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(ModelMap model,
                              @RequestParam(value = "count", required = false, defaultValue = "100") Integer count) {
        model.addAttribute("users", userService.getUsers(count));

        return "users";
    }

    @GetMapping("/users/newUser")
    public String getNewUserPage(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping("/users/newUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/editUser")
    public String getUser(ModelMap model,
                          @RequestParam(value = "id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PatchMapping(value = "/users")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
