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

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createNewUser(@RequestBody User user){
        userService.register(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updateUser(@PathVariable Integer id, @RequestBody User user){
        User oldUser = userService.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setBirthDay(user.getBirthDay());
        oldUser.setName(user.getName());
        userService.updateUser(oldUser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUser(@PathVariable Integer id){
        User user = userService.getById(id);
        userService.remove(user);
    }
}
