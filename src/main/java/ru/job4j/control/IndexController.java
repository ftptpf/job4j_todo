package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.ItemService;

@Controller
public class IndexController {

    private final ItemService service;


    public IndexController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
