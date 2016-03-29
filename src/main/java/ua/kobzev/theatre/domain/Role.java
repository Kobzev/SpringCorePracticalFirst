package ua.kobzev.theatre.domain;

import ua.kobzev.theatre.enums.UserRoles;

import javax.persistence.Id;

/**
 * Created by kkobziev on 3/28/16.
 */
public class Role {
    @Id
    private Long id;

    private String userName;
    private UserRoles userRole;

    public Role(String userName, UserRoles userRole){
        this.userName = userName;
        this.userRole = userRole;
    }

    public Role(Long id, String userName, UserRoles userRole) {
        this.id = id;
        this.userName = userName;
        this.userRole = userRole;
    }

    public Role() {

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }
}
