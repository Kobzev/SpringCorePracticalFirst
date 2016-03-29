package ua.kobzev.theatre.domain.dto;

import ua.kobzev.theatre.domain.Ticket;

import java.time.LocalDateTime;

/**
 * Created by kkobziev on 3/20/16.
 */
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

    public TicketDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
