package ru.job4j.control;

import ru.job4j.service.ItemService;

public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }
}
