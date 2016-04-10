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
@XmlRootElement(name = "getEventByNameResponse")
public class GetEventByNameResponse {

    @XmlElement(required = true)
    protected Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event value) {
        this.event = value;
    }

}
