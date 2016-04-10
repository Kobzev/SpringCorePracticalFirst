package ua.kobzev.theatre.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.kobzev.theatre.soap.request.CreateEventRequest;
import ua.kobzev.theatre.soap.request.DeleteEventByNameRequest;
import ua.kobzev.theatre.soap.request.GetEventByNameRequest;
import ua.kobzev.theatre.soap.request.UpdateEventRequest;
import ua.kobzev.theatre.soap.responce.CreateEventResponse;
import ua.kobzev.theatre.soap.responce.DeleteEventByNameResponse;
import ua.kobzev.theatre.soap.responce.GetEventByNameResponse;
import ua.kobzev.theatre.service.EventService;
import ua.kobzev.theatre.soap.responce.UpdateEventResponse;

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEventRequest")
    @ResponsePayload
    public CreateEventResponse createEvent(@RequestPayload CreateEventRequest request) {
        CreateEventResponse response = new CreateEventResponse();
        eventService.create(request.getEvent());
        response.setEvent(eventService.getByName(request.getEvent().getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEventByNameRequest")
    @ResponsePayload
    public DeleteEventByNameResponse deleteEventByName(@RequestPayload DeleteEventByNameRequest request) {
        DeleteEventByNameResponse response = new DeleteEventByNameResponse();
        response.setAnswer("Deleting event " + eventService.remove(eventService.getByName(request.getEventName())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEventRequest")
    @ResponsePayload
    public UpdateEventResponse updateEvent(@RequestPayload UpdateEventRequest request) {
        UpdateEventResponse response = new UpdateEventResponse();
        eventService.updateEvent(request.getEvent());
        response.setEvent(eventService.getByName(request.getEvent().getName()));
        return response;
    }
}
