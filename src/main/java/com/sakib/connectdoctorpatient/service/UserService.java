package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.dao.UserDao;
import com.sakib.connectdoctorpatient.dto.LoginDTO;
import com.sakib.connectdoctorpatient.model.User;
import org.springframework.stereotype.Service;

/**
 * @author sakib.khan
 * @since 3/1/22
 */
@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByCredentials(LoginDTO loggedUser) {
        User user = User.builder()
                .email(loggedUser.getEmail())
                .password(loggedUser.getPassword())
                .build();

        return userDao.findByCredentials(user).orElse(null);
    }
}
