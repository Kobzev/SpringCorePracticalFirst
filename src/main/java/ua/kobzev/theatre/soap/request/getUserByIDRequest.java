package ua.kobzev.theatre.soap.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by kkobziev on 4/10/16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ua.kobzev.theatre/soap",
        name = "", propOrder = {
        "userID"
})
@XmlRootElement(name = "getUserByIDRequest")
public class GetUserByIDRequest {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int value) {
        this.userID = value;
    }
}
