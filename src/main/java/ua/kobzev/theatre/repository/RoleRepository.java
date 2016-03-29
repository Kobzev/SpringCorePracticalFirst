package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.Role;

import java.util.List;

/**
 * Created by kkobziev on 3/28/16.
 */
public interface RoleRepository {

    List<Role> findAllByUserEmail(String email);
    void save(Role role);
}
