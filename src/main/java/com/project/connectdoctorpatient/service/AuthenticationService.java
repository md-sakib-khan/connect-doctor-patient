package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dto.LoginDTO;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.util.SessionUtil;
import com.project.connectdoctorpatient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/7/22
 */
@Service
public class AuthenticationService {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private UserService userService;

    public void authenticate(LoginDTO user) throws NotFoundException {
        User loggedUser = userService.findByCredentials(user);

        if (nonNull(loggedUser)) {
            SessionUtil.setAttribute("loggedUser", loggedUser);
        } else {
            throw new NotFoundException(msa.getMessage("exception.user.none"));
        }
    }

}
