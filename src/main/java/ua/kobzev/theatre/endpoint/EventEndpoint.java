package ua.kobzev.theatre.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.domain.generated.GetEventByNameRequest;
import ua.kobzev.theatre.domain.generated.GetEventByNameResponse;
import ua.kobzev.theatre.service.EventService;

/**
 * Created by kkobziev on 4/7/16.
 */
@Endpoint
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/soap";

    @Autowired
    private EventService eventService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventByNameRequest")
    @ResponsePayload
    public GetEventByNameResponse getEventByName(@RequestPayload GetEventByNameRequest request) {
        GetEventByNameResponse response = new GetEventByNameResponse();
        response.setEvent(eventService.getByName(request.getEventName()));
        return response;
    }
}
