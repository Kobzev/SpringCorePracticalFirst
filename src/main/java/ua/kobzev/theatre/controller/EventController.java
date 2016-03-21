package ua.kobzev.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.service.EventService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Model model){
        model.addAttribute("events", eventService.findAllEvents());
        return "events";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createEvent(Model model){
        model.addAttribute("event", new Event());
        return "event/createEvent";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createEventAction(Model model, Event event){
        eventService.create(event);
        model.addAttribute("events", eventService.findAllEvents());
        return "events";
    }

    @RequestMapping(value = "/delete")
    public String deleteEvent(Model model, @RequestParam("id") String name){
        eventService.remove(eventService.getByName(name));
        model.addAttribute("events", eventService.findAllEvents());
        return "events";
    }

    @RequestMapping(value = "/choosename", method = RequestMethod.GET)
    public String chooseName(Model model){
        Event event = new Event();
        event.setName("Pick name");
        model.addAttribute("event", event);
        return "event/findbyname";
    }


    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findByName(Model model, @RequestParam("name") String name){
        List<Event> events = new ArrayList<>();
        Event ev = eventService.getByName(name);
        if (nonNull(ev)) events.add(ev);

        model.addAttribute("events", events);
        return "events";
    }

}
