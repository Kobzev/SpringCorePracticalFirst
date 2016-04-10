//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.07 at 03:13:55 PM EEST 
//


package ua.kobzev.theatre.domain.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.kobzev.theatre.domain.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
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
    public GetEventByNameResponse createGetEventByIDResponse() {
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
