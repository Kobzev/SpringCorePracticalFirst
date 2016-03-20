package ua.kobzev.theatre.controller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.dto.TicketDto;
import ua.kobzev.theatre.service.BookingService;
import ua.kobzev.theatre.service.EventService;
import ua.kobzev.theatre.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kkobziev on 3/20/16.
 */
@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(){
        return "reports";
    }

    @RequestMapping(value = "/chooseeventname", method = RequestMethod.GET)
    public String chooseEventName(Model model){
        Event event = new Event();
        event.setName("Pick name");
        model.addAttribute("event", event);
        return "report/findbyeventname";
    }

    private List<TicketDto> findTicketsByEvent(Event event){
        Event ev = eventService.getByName(event.getName());

        final List<TicketDto> tikets = new ArrayList<>();

        eventService.getAll().stream()
                .filter(assignedEvent -> assignedEvent.getEvent().equals(ev))
                .forEach(assignedEvent -> bookingService.getTicketsForEvent(assignedEvent.getEvent(), assignedEvent.getDate())
                        .stream()
                        .forEach(ticket -> tikets.add(new TicketDto(ticket))));

        return tikets;
    }

    @RequestMapping(value = "/findbyeventname", method = RequestMethod.GET)
    public String findByEventName(Model model, Event event){
        model.addAttribute("tickets", findTicketsByEvent(event));
        return "reports";
    }

    @RequestMapping(value = "/findbyeventname", method = RequestMethod.GET, headers = "application/pdf")
    public ModelAndView findByEventNamePDF(Model model, Event event){
        List<TicketDto> ticketDtoList = findTicketsByEvent(event);

        JRDataSource datasource = new JRBeanCollectionDataSource(ticketDtoList);

        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();

        parameterMap.put("datasource", datasource);

        ModelAndView modelAndView = new ModelAndView("pdfReport", parameterMap);
        return modelAndView;
    }

    @RequestMapping(value = "/chooseusername", method = RequestMethod.GET)
    public String chooseUserName(Model model){
        User user = new User();
        user.setName("Pick name");
        model.addAttribute("user", user);
        return "report/findbyusername";
    }

    private List<TicketDto> findTicketsByUser(User user){
        List<User> users = userService.getUsersByName(user.getName());

        final List<TicketDto> tikets = new ArrayList<>();

        users.stream()
                .forEach(usr ->
                        userService.getBookedTickets(usr)
                                .stream()
                                .forEach(ticket -> tikets.add(new TicketDto(ticket))));

        return tikets;
    }

    @RequestMapping(value = "/findbyusername", method = RequestMethod.GET)
    public String findByUserName(Model model, User user){
        model.addAttribute("tickets", findTicketsByUser(user));
        return "reports";
    }

    @RequestMapping(value = "/findbyusername", method = RequestMethod.GET, headers = "application/pdf")
    public ModelAndView findByUserNamePDF(Model model, User user){
        List<TicketDto> ticketDtoList = findTicketsByUser(user);

        JRDataSource datasource = new JRBeanCollectionDataSource(ticketDtoList);

        // parameterMap is the Model of our application
        Map<String,Object> parameterMap = new HashMap<String,Object>();

        parameterMap.put("datasource", datasource);

        ModelAndView modelAndView = new ModelAndView("pdfReport", parameterMap);
        return modelAndView;
    }
}
