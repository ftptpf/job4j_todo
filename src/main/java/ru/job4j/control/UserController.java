package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.User;
import ru.job4j.service.UserService;
import ru.job4j.util.UserUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user, HttpSession session) {
        List<User> list = (List<User>) service.create(user);
        if (list.isEmpty()) {
            model.addAttribute("message", "Ошибка регистрации. Пользователь с таким логином уже существует");
        } else {
            model.addAttribute("message", "Пользователь успешно зарегистрирован");
        }
        UserUtil.checkAndSetGuestName(model, session);
        return "registration";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        UserUtil.checkAndSetGuestName(model, session);
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        List<User> list = service.findByLoginAndPassword(
                user.getLogin(),
                user.getPassword());
        if (list.isEmpty()) {
            return "redirect:/login?fail=true";
        }
        User userDb = list.get(0);
        session.setAttribute("user", userDb);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }



}
