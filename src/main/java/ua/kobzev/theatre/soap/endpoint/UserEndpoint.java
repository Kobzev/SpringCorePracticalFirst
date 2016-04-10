package ua.kobzev.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.UserService;
import ua.kobzev.theatre.soap.request.CreateUserRequest;
import ua.kobzev.theatre.soap.request.DeleteUserByIdRequest;
import ua.kobzev.theatre.soap.request.GetUserByIDRequest;
import ua.kobzev.theatre.soap.request.UpdateUserRequest;
import ua.kobzev.theatre.soap.responce.CreateUserResponse;
import ua.kobzev.theatre.soap.responce.DeleteUserByIdResponse;
import ua.kobzev.theatre.soap.responce.GetUserByIDResponse;
import ua.kobzev.theatre.soap.responce.UpdateUserResponse;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by kkobziev on 4/7/16.
 */
@Endpoint
@XmlTransient
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://ua.kobzev.theatre/soap";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIDRequest")
    @ResponsePayload
    public GetUserByIDResponse getUserByID(@RequestPayload GetUserByIDRequest request) {
        GetUserByIDResponse response = new GetUserByIDResponse();
        response.setUser(userService.getById(request.getUserID()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        User user = request.getUser();
        user.setPassword("123");
        userService.register(user);
        response.setUser(userService.getUserByEmail(request.getUser().getEmail()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserByIDRequest")
    @ResponsePayload
    public DeleteUserByIdResponse deleteUserByID(@RequestPayload DeleteUserByIdRequest request) {
        DeleteUserByIdResponse response = new DeleteUserByIdResponse();
        response.setAnswer("Deleting user " + userService.remove(userService.getById(request.getUserID())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUserRequest(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        User oldUser = userService.getById(request.getUser().getId());
        if (request.getUser().getEmail() != null) oldUser.setEmail(request.getUser().getEmail());
        if (request.getUser().getBirthDay() != null) oldUser.setBirthDay(request.getUser().getBirthDay());
        if (request.getUser().getName() != null) oldUser.setName(request.getUser().getName());
        userService.updateUser(oldUser);
        response.setUser(userService.getById(request.getUser().getId()));
        return response;
    }
}
