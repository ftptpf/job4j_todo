package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.persistence.UserDbStore;

import java.util.List;

@Service
public class UserService {
    private final UserDbStore store;

    public UserService(UserDbStore store) {
        this.store = store;
    }

    public User create(User user) {
        return store.create(user);
    }

    public List<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }
}
