package ua.kobzev.theatre.soap.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by kkobziev on 4/10/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "eventName"
})
@XmlRootElement(name = "deleteEventByNameRequest")
public class DeleteEventByNameRequest {
    private String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String value) {
        this.eventName = value;
    }
}
