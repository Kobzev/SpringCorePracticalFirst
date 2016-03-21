package ua.kobzev.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String testUser(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "user/createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUserAction(Model model, User user){
        userService.register(user);
        return "users";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Integer id){
        userService.remove(userService.getById(id));
        return "users";
    }

    @RequestMapping(value = "/choosename", method = RequestMethod.GET)
    public String findByName(Model model){
        User user = new User();
        user.setName("Pick name");
        model.addAttribute("user", user);
        return "user/findbyname";
    }

    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findByNameAction(Model model, User user){
        model.addAttribute("users", userService.getUsersByName(user.getName()));
        return "users";
    }

    @RequestMapping(value = "/chooseid", method = RequestMethod.GET)
    public String findById(Model model){
        User user = new User();
        user.setId(0);
        model.addAttribute("user", user);
        return "user/findbyid";
    }

    @RequestMapping(value = "/chooseemail", method = RequestMethod.GET)
    public String findByEmail(Model model){
        User user = new User();
        user.setEmail("Pick email");
        model.addAttribute("user", user);
        return "user/findbyemail";
    }

    @RequestMapping(value = "/findbyemail", method = RequestMethod.GET)
    public String findByEmailAction(Model model, User user){
        List<User> users = new ArrayList<>();
        User usr = userService.getUserByEmail(user.getEmail());
        if (nonNull(usr)) users.add(usr);

        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.GET)
    public String findByIDAction(Model model, User user){
        List<User> users = new ArrayList<>();
        User usr = userService.getById(user.getId());
        if (nonNull(usr)) users.add(usr);

        model.addAttribute("users", users);
        return "users";
    }
}
