package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public boolean register(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        Serializable id = session.getIdentifier(user);
        session.close();
        return ((Integer) id)>0;
    }

    public boolean remove(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User userLoad = session.load(User.class, user.getId());
        session.delete(userLoad);
        tx.commit();
        Serializable ids = session.getIdentifier(userLoad);
        session.close();
        return ((Integer) ids)>0;
    }

    public User getById(Integer id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select * from users where email = :email").addEntity(User.class);
        query.setString("email", email);
        List<User> userList = query.list();

        session.close();

        if (userList.size() == 0) return null;
        return userList.get(0);
    }

    public List<User> getUsersByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select * from users where name like :name").addEntity(User.class);
        query.setString("name", name);
        List<User> userList = query.list();

        session.close();
        return userList;
    }
}
