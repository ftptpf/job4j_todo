package ru.job4j.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserDbStore {
    private final SessionFactory sf;

    public UserDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Optional<User> create(User user) {
        try {
            tx(session -> session.save(user));
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public List<User> findByLoginAndPassword(String login, String password) {
        return tx(session -> session.createQuery("FROM User WHERE login = :userLogin AND password = :userPassword")
                .setParameter("userLogin", login)
                .setParameter("userPassword", password)
                .list());
    }

}
