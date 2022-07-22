package ru.job4j.control;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Item;
import ru.job4j.service.ItemService;

public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Item item) {
        service.create(item);
        return "redirect:/index";
    }

    @GetMapping("/detail/{itemId}")
    public String detail(Model model, @PathVariable("itemId") int id) {
        model.addAttribute("item", service.findById(id));
        return "detail";
    }
}
