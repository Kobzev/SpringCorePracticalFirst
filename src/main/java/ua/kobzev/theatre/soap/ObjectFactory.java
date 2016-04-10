package ua.kobzev.theatre.soap;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.soap.request.GetEventByNameRequest;
import ua.kobzev.theatre.soap.request.GetUserByIDRequest;
import ua.kobzev.theatre.soap.responce.GetEventByNameResponse;
import ua.kobzev.theatre.soap.responce.GetUserByIDResponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Event_QNAME = new QName("", "event");
    private final static QName _User_QNAME = new QName("", "user");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.kobzev.theatre.domain.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEventByNameResponse }
     * 
     */
    public GetEventByNameResponse createGetEventByNameResponse() {
        return new GetEventByNameResponse();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link GetEventByNameRequest }
     * 
     */
    public GetEventByNameRequest createGetEventByIDRequest() {
        return new GetEventByNameRequest();
    }

    /**
     * Create an instance of {@link GetUserByIDRequest }
     * 
     */
    public GetUserByIDRequest createGetUserByIDRequest() {
        return new GetUserByIDRequest();
    }

    /**
     * Create an instance of {@link GetUserByIDResponse }
     * 
     */
    public GetUserByIDResponse createGetUserByIDResponse() {
        return new GetUserByIDResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
