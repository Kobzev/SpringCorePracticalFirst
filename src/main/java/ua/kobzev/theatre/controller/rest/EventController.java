package ua.kobzev.theatre.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.service.EventService;

import java.util.List;

/**
 * Created by kkobziev on 4/5/16.
 */
@RestController(value = "restEvent")
@RequestMapping("rest/events")
public class EventController {
    /*
    GET /events - Retrieves a list of events
    GET /events/name - Retrieves a specific event
    POST /events - Creates a new event
    PUT /events/name - Updates event with name
    DELETE /events/name - Deletes event with name
    */

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Event> getAllEvents(){
        return eventService.findAllEvents();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Event getEventByName(@PathVariable String name){
        return eventService.getByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createNewEvent(@RequestBody Event event){
        eventService.create(event);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updateEvent(@PathVariable String name, @RequestBody Event event){
        Event oldEvent = eventService.getByName(name);
        oldEvent.setBasePrice(event.getBasePrice());
        oldEvent.setRate(event.getRate());
        eventService.updateEvent(oldEvent);
    }

    @RequestMapping(method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteEvent(@PathVariable String name){
        Event event = eventService.getByName(name);
        eventService.remove(event);
    }
}
