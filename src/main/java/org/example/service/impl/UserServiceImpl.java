package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.Account;
import org.example.entity.Student;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = mapper.SelectUserByName(username);
        if (account==null){
            throw new UsernameNotFoundException("用户名或者密码错误");
        }

        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
    @Override
    public List<Student> getStudentList() {
        return mapper.getStudentList();
    }
}
