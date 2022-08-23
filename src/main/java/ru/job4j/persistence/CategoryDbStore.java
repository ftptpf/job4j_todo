package ru.job4j.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;
import ru.job4j.util.DbConnect;

import java.util.List;

@Repository
public class CategoryDbStore {
    private final SessionFactory sf;

    public CategoryDbStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Category> findAll() {
        return DbConnect.tx(session -> session.createQuery("from Category ORDER BY id").list(), sf);
    }

    public Category findById(int id) {
        return (Category) DbConnect.tx(session -> session.createQuery(
                "from Category where id = :fId").setParameter("fId", id).uniqueResult(), sf);
    }
}
