package ua.kobzev.theatre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kobzev.theatre.domain.dto.TicketDto;
import ua.kobzev.theatre.service.BookingService;

/**
 * Created by kkobziev on 4/2/16.
 */
@Controller
@RequestMapping("/tickets")
public class TicketsController {

    @Autowired
    private BookingService bookingService;


    @RequestMapping(method = RequestMethod.GET)
    public String bookTicketPage(Model model){
        model.addAttribute("ticket", new TicketDto());
        return "bookticket";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String bookTicket(Model model,
                             TicketDto ticketDto){


        if (bookingService.bookTicket(SecurityContextHolder.getContext().getAuthentication().getName(),
                                    ticketDto.getEventid(), ticketDto.getSeat())) {
            return "redirect:/";
        }
        model.addAttribute("textError", "This ticket are booked. Try another");
        return "redirect:/tickets";
    }
}
