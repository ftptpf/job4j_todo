package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.service.CategoryService;
import ru.job4j.service.ItemService;
import ru.job4j.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("categories", categoryService.findAll());
        return "add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Item item, HttpSession session, HttpServletRequest req) {
        String[] parameters = req.getParameterValues("category.id");
        setCategories(item, parameters);
        User user = (User) session.getAttribute("user");
        item.setUser(user);
        itemService.create(item);
        return "redirect:/index";
    }

    @GetMapping("/detail/{itemId}")
    public String detail(Model model, @PathVariable("itemId") int id, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("item", itemService.findById(id));
        return "detail";
    }

    @GetMapping("/edit/{itemId}")
    public String edit(Model model, @PathVariable("itemId") int id, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "edit";
    }

    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") int id) {
        Item item = itemService.findById(id);
        itemService.delete(item);
        return "redirect:/index";
    }

    @PostMapping("/complete")
    public String complete(Model model, @ModelAttribute Item item, HttpServletRequest req) {
        String[] parameters = req.getParameterValues("category.id");
        setCategories(item, parameters);
        model.addAttribute("item", itemService.update(item));
        return "redirect:/detail/" + item.getId();
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Item item, HttpServletRequest req) {
        String[] parameters = req.getParameterValues("category.id");
        setCategories(item, parameters);
        itemService.update(item);
        return "redirect:/index";
    }

    public void setCategories(Item item, String[] parameters) {
        for (String id : parameters) {
            int categoryId = Integer.parseInt(id);
            item.addCategory(categoryService.findById(categoryId));
        }
    }

}
