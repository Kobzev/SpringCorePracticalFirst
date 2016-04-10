package ua.kobzev.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.service.UserService;
import ua.kobzev.theatre.soap.request.GetUserByIDRequest;
import ua.kobzev.theatre.soap.responce.GetUserByIDResponse;

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
}
