package web;


import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

public class Main {


    public static void main(String[] args) {
        System.out.println("At least it's working !");

        User user = new User("Pavel", "Konstantinov", (byte) 23);
        System.out.println(user);

        final UserService userService = new UserServiceImpl();
        System.out.println(userService.getUserById(2L));

        UserServiceImpl userService1 = new UserServiceImpl();
        userService1.saveUser(user);

    }
}