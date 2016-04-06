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
    public @ResponseBody List<Event> getAllEvents(){
        return eventService.findAllEvents();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Event getEventByName(@PathVariable String name){
        return eventService.getByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody Event createNewEvent(@RequestBody Event event){
        eventService.create(event);
        return eventService.getByName(event.getName());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public @ResponseBody Event updateEvent(@PathVariable String name, @RequestBody Event event){
        Event oldEvent = eventService.getByName(name);
        if (event.getBasePrice() != null) oldEvent.setBasePrice(event.getBasePrice());
        if (event.getRate() != null) oldEvent.setRate(event.getRate());
        eventService.updateEvent(oldEvent);
        return eventService.getByName(name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteEvent(@PathVariable String name){
        Event event = eventService.getByName(name);
        eventService.remove(event);
    }
}
