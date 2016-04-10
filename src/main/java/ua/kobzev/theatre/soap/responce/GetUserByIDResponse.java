package ua.kobzev.theatre.soap.responce;

import ua.kobzev.theatre.domain.User;

import javax.xml.bind.annotation.*;

/**
 * Created by kkobziev on 4/10/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ua.kobzev.theatre/soap",
        name = "", propOrder = {
        "user"
})
@XmlRootElement(name = "getUserByIDResponse")
public class GetUserByIDResponse {

    @XmlElement(required = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        this.user = value;
    }
}
