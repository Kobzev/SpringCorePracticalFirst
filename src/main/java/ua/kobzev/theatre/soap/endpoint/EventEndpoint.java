package ua.kobzev.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.soap.request.GetEventByNameRequest;
import ua.kobzev.theatre.soap.responce.GetEventByNameResponse;
import ua.kobzev.theatre.service.EventService;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by kkobziev on 4/7/16.
 */
@Endpoint
@XmlTransient
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://ua.kobzev.theatre/soap";

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
