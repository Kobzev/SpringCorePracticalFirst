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
        "userID"
})
@XmlRootElement(name = "deleteUserByIDRequest")
public class DeleteUserByIdRequest {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int value) {
        this.userID = value;
    }
}
