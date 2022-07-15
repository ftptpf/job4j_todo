package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.persistence.ItemDbStore;

@Service
public class ItemService {
    private final ItemDbStore store;

    public ItemService(ItemDbStore store) {
        this.store = store;
    }
}
