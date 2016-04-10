package ua.kobzev.theatre.soap.responce;

import ua.kobzev.theatre.domain.Event;

import javax.xml.bind.annotation.*;

/**
 * Created by kkobziev on 4/10/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "event"
})
@XmlRootElement(name = "updateEventResponse")
public class UpdateEventResponse {
    @XmlElement(required = true)
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
