package top.mygld.problemnote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.pojo.User;
import top.mygld.problemnote.service.UserService;
import top.mygld.problemnote.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
} 