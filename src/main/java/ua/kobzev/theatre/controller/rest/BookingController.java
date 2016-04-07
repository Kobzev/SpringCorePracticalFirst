package ua.kobzev.theatre.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.dto.BookedDto;
import ua.kobzev.theatre.service.BookingService;

import java.util.List;

/**
 * Created by kkobziev on 4/5/16.
 */
@RestController(value = "restBooking")
@RequestMapping("rest/tickets")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST)
    public Ticket bookTicket(@RequestBody BookedDto dto){
        bookingService.bookTicket(dto.getUserEmail(), dto.getAssignedEventId(), dto.getSeat());
        return bookingService.findTicketByDTO(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> findAll(){
        return bookingService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket findById(@PathVariable Integer id){
        return bookingService.findTicketById(id);
    }
}
