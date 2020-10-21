package com.project.project1.service;

import com.project.project1.dao.SUserDao;
import com.project.project1.entity.User_s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceIlpl implements UserService {

    @Autowired
    private SUserDao userDao;


    @Override
    public String login(String studentId) {
        return userDao.login(studentId);
    }

    @Override
    public String getRealName(String studentId, String password) {
        return userDao.getRealName(studentId,password);
    }

    @Override
    public String getRealNameById(String studentId) {
        return userDao.getRealameById(studentId);
    }

    @Override
    public void register(User_s user_s) {
        userDao.register(user_s);
    }
    @Override
    public void inputByUserS(User_s user_s) {
        userDao.inputByUserS(user_s);
    }


}
