package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Item;
import ru.job4j.service.ItemService;
import ru.job4j.util.UserUtil;

import javax.servlet.http.HttpSession;

@Controller
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        return "add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Item item) {
        service.create(item);
        return "redirect:/index";
    }

    @GetMapping("/detail/{itemId}")
    public String detail(Model model, @PathVariable("itemId") int id, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("item", service.findById(id));
        return "detail";
    }

    @GetMapping("/edit/{itemId}")
    public String edit(Model model, @PathVariable("itemId") int id, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("item", service.findById(id));
        return "edit";
    }

    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") int id) {
        Item item = service.findById(id);
        service.delete(item);
        return "redirect:/index";
    }

    @PostMapping("/complete")
    public String complete(Model model, @ModelAttribute Item item) {
        model.addAttribute("item", service.update(item));
        return "redirect:/detail/" + item.getId();
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Item item) {
        service.update(item);
        return "redirect:/index";
    }

}
