package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.persistence.CategoryDbStore;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryDbStore store;

    public CategoryService(CategoryDbStore store) {
        this.store = store;
    }

    public List<Category> findAll() {
        return store.findAll();
    }

    public Category findById(int id) {
        return store.findById(id);
    }

}
