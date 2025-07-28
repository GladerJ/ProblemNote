package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.User;

public interface UserService {
    User getUserByUsername(String username);
    int addUser(User user);
} 