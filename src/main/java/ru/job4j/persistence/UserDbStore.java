package ru.job4j.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;
import ru.job4j.util.DbConnect;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDbStore {
    private final SessionFactory sf;

    public UserDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> create(User user) {
        try {
            DbConnect.tx(session -> session.save(user), sf);
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public List<User> findByLoginAndPassword(String login, String password) {
        return DbConnect.tx(session -> session.createQuery(
                "FROM User WHERE login = :userLogin AND password = :userPassword")
                .setParameter("userLogin", login)
                .setParameter("userPassword", password)
                .list(), sf);
    }

}
