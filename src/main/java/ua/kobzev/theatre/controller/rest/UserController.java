package ua.kobzev.theatre.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.UserService;

import java.util.List;

/**
 * Created by kkobziev on 4/5/16.
 */
@RestController(value = "restUser")
@RequestMapping("rest/users")
public class UserController {
    /*
    GET /users - Retrieves a list of users
    GET /users/12 - Retrieves a specific user
    POST /users - Creates a new user
    PUT /users/12 - Updates user #12
    DELETE /users/12 - Deletes user #12
    */

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User createNewUser(@RequestBody User user){
        userService.register(user);
        return userService.getUserByEmail(user.getEmail());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody User updateUser(@PathVariable Integer id, @RequestBody User user){
        User oldUser = userService.getById(id);
        if (user.getEmail() != null) oldUser.setEmail(user.getEmail());
        if (user.getBirthDay() != null) oldUser.setBirthDay(user.getBirthDay());
        if (user.getName() != null) oldUser.setName(user.getName());
        userService.updateUser(oldUser);
        return userService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer id){
        User user = userService.getById(id);
        userService.remove(user);
    }
}
