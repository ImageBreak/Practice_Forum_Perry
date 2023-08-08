package net.perry.forum.service;

import net.perry.forum.domain.User;

public interface UserService {
    int register(User user);

    User login(String phone, String pwd);
}
