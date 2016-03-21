package ua.kobzev.theatre.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.kobzev.theatre.domain.Ticket;

import java.time.LocalDateTime;

/**
 * Created by kkobziev on 3/20/16.
 */
@Getter
@Setter
@NoArgsConstructor
public class TicketDto {

    private Integer id;
    private String user;
    private String event;
    private LocalDateTime eventDate;
    private Integer seat;
    private Double price;

    public TicketDto(Ticket ticket){
        this.id     = ticket.getId();
        this.event  = ticket.getAssignedEvent().getEvent().getName();
        this.eventDate = ticket.getAssignedEvent().getDate();
        this.user   = ticket.getUser().getName();
        this.seat   = ticket.getSeat();
        this.price  = ticket.getPrice();

    }

}
