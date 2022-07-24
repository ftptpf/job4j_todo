package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Item;

import java.util.List;

@Repository
public class ItemDbStore {
    private final SessionFactory sf;

    public ItemDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item create(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List list = session.createQuery("FROM Item ORDER BY id").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public List<Item> findAllDone() {
        Session session = sf.openSession();
        session.beginTransaction();
        List list = session.createQuery("FROM Item WHERE done = true ORDER BY id").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Item> findAllToday() {
        Session session = sf.openSession();
        session.beginTransaction();
        List list = session.createQuery("FROM Item WHERE created >= CURRENT_DATE ORDER BY id ").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

}
