package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.ItemService;
import ru.job4j.util.UserUtil;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final ItemService service;

    public IndexController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("items", service.findAll());
        return "index";
    }

    @GetMapping("/done")
    public String done(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("items", service.findAllDone());
        return "index";
    }

    @GetMapping("/today")
    public String today(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("items", service.findAllToday());
        return "index";
    }

}
