package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Item;
import ru.job4j.service.ItemService;

@Controller
public class IndexController {

    private final ItemService service;

    public IndexController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("items", service.findAll());
        return "index";
    }

    @GetMapping("/done")
    public String done(Model model) {
        model.addAttribute("items", service.findAllDone());
        return "index";
    }

    @GetMapping("/today")
    public String today(Model model) {
        model.addAttribute("items", service.findAllToday());
        return "index";
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
}
