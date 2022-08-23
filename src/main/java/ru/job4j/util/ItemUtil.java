package ru.job4j.util;

import ru.job4j.model.Item;
import ru.job4j.service.CategoryService;

import java.util.Arrays;

public class ItemUtil {

    private ItemUtil() {
    }

    public static void setCategories(Item item, String[] parameters, CategoryService categoryService) {
        var categoriesID = Arrays.stream(parameters)
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int id : categoriesID) {
            item.addCategory(categoryService.findById(id));
        }
    }
}
