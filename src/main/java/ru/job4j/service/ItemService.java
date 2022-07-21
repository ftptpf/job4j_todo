package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Item;
import ru.job4j.persistence.ItemDbStore;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService {
    private final ItemDbStore store;

    public ItemService(ItemDbStore store) {
        this.store = store;
    }

    public Item create(Item item) {
        Item one = new Item();
        one.setDescription(item.getDescription());
        one.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        return store.create(one);
    }

    public void update(Item item) {
        store.update(item);
    }

    public void delete(Item item) {
        store.delete(item);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public List<Item> findAllDone() {
        return  store.findAllDone();
    }

    public List<Item> findAllToday() {
        return store.findAllToday();
    }
}
