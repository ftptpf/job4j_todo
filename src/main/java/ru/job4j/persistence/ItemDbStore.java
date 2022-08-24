package ru.job4j.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Item;
import ru.job4j.util.DbConnect;

import java.util.List;

@Repository
public class ItemDbStore {
    private final SessionFactory sf;

    public ItemDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item saveOrUpdate(Item item) {
        return DbConnect.tx(session -> {
            session.saveOrUpdate(item);
            return item;
        }, sf);
    }

    public void delete(Item item) {
        DbConnect.tx(session -> {
            session.delete(item);
            return true;
        }, sf);
    }

    public List<Item> findAll() {
        return DbConnect.tx(session -> session.createQuery(
                "SELECT DISTINCT i FROM Item i JOIN FETCH i.categories ORDER BY i.id")
                .list(), sf);
    }

    public Item findById(int id) {
        return (Item) DbConnect.tx(session -> session.createQuery(
                "SELECT DISTINCT i FROM Item i JOIN FETCH i.categories WHERE i.id = :fId")
                        .setParameter("fId", id).uniqueResult(), sf);
    }

    public List<Item> findAllDone() {
        return DbConnect.tx(session -> session.createQuery(
                "SELECT DISTINCT i FROM Item i JOIN FETCH i.categories WHERE i.done = true ORDER BY i.id")
                .list(), sf);
    }

    public List<Item> findAllToday() {
        return DbConnect.tx(session -> session.createQuery(
                "SELECT DISTINCT i FROM Item i JOIN FETCH i.categories WHERE i.created >= CURRENT_DATE ORDER BY i.id")
                .list(), sf);
    }

}
