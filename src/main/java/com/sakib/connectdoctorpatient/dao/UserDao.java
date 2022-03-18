package com.sakib.connectdoctorpatient.dao;

import com.sakib.connectdoctorpatient.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 3/1/22
 */
@Repository
public class UserDao extends ParentDao<User> {

    public Optional<User> findByCredentials(User user) {
        return em.createNamedQuery("User.findByCredentials", User.class)
                .setParameter("email", user.getEmail())
                .setParameter("password", user.getPassword())
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }
}
