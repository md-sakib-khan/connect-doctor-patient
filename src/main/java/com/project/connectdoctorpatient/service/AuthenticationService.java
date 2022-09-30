package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.dto.LoginDTO;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.model.User;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/7/22
 */
@Service
public class AuthenticationService {

    private final MessageSourceAccessor msa;

    private final UserService userService;

    public AuthenticationService(MessageSourceAccessor msa, UserService userService) {
        this.msa = msa;
        this.userService = userService;
    }

    public void authenticate(LoginDTO user) throws NotFoundException {
        User loggedUser = userService.findByCredentials(user);

        if (nonNull(loggedUser)) {
            SessionUtil.setAttribute("loggedUser", loggedUser);
        } else {
            throw new NotFoundException(msa.getMessage("exception.user.none"));
        }
    }

}
