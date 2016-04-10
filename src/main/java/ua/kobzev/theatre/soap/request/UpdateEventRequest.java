package ua.kobzev.theatre.soap.request;

import ua.kobzev.theatre.domain.Event;

import javax.xml.bind.annotation.*;

/**
 * Created by kkobziev on 4/10/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "event"
})
@XmlRootElement(name = "updateEventRequest")
public class UpdateEventRequest {
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
