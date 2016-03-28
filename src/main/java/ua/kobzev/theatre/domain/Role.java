package ua.kobzev.theatre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.kobzev.theatre.enums.UserRoles;

import javax.persistence.Id;

/**
 * Created by kkobziev on 3/28/16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Long id;

    private String userName;
    private UserRoles userRole;

    public Role(String userName, UserRoles userRole){
        this.userName = userName;
        this.userRole = userRole;
    }
}
