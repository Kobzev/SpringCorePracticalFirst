package ua.kobzev.theatre.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.domain.generated.GetUserByIDRequest;
import ua.kobzev.theatre.domain.generated.GetUserByIDResponse;
import ua.kobzev.theatre.service.UserService;

/**
 * Created by kkobziev on 4/7/16.
 */
@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/soap";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIDRequest")
    @ResponsePayload
    public GetUserByIDResponse getUserByID(@RequestPayload GetUserByIDRequest request) {
        GetUserByIDResponse response = new GetUserByIDResponse();
        response.setUser(userService.getById(request.getUserID()));
        return response;
    }
}
