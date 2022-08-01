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

    public Item create(Item item) {
        return (Item) DbConnect.tx(session -> session.save(item), sf);
    }

    public Item update(Item item) {
        return DbConnect.tx(session -> {
            session.update(item);
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
        return DbConnect.tx(session -> session.createQuery("FROM Item ORDER BY id")
                .list(), sf);
    }

    public Item findById(int id) {
        return DbConnect.tx(session -> session.get(Item.class, id), sf);
    }

    public List<Item> findAllDone() {
        return DbConnect.tx(session -> session.createQuery("FROM Item WHERE done = true ORDER BY id")
                .list(), sf);
    }

    public List<Item> findAllToday() {
        return DbConnect.tx(session -> session.createQuery("FROM Item WHERE created >= CURRENT_DATE ORDER BY id ")
                .list(), sf);
    }

}
