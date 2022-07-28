package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDbStore {
    private final SessionFactory sf;

    public UserDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> create(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
        return Optional.of(user);
    }

    public List<User> findByLoginAndPassword(String login, String password) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE login = :userLogin AND password = :userPassword");
        query.setParameter("userLogin", login);
        query.setParameter("userPassword", password);
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

}
