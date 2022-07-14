package ru.job4j.service;

import ru.job4j.persistence.ItemDbStore;

public class ItemService {
    private final ItemDbStore store;

    public ItemService(ItemDbStore store) {
        this.store = store;
    }
}
