package com.codegym.service.impl;

import com.codegym.model.User;
import com.codegym.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static Map<Integer, User> users;
    static {
        users = new HashMap<>();
        users.put(1, new User(1, "Michael", "Scofield", "03245435984", 20, "mike@gmail.com"));
        users.put(2, new User(2, "Nguyen Van", "A", "0985222101", 25, "nguyenVanA@gmail.com"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public void update(int id, User user) {
        users.put(id, user);
    }

    @Override
    public void remove(int id) {
        users.remove(id);
    }
}
