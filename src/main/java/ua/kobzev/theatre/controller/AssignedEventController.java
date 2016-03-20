package ua.kobzev.theatre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kkobziev on 3/19/16.
 */
@Controller
@RequestMapping("/assignedevents")
public class AssignedEventController {

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(){
        return "assignedevents";
    }
}
