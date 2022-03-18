package com.sakib.connectdoctorpatient.service;

import com.sakib.connectdoctorpatient.exception.AccessDeniedException;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Role;
import com.sakib.connectdoctorpatient.util.AuthorizationUtil;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sakib.khan
 * @since 2/25/22
 */
@Service
public class AccessManager {

    @Autowired
    private MessageSourceAccessor msa;

    public void checkAccess(Action action) throws AccessDeniedException {
        String requestURI = RequestUtil.getRequestURI();
        Role userRole = SessionUtil.getUserRole();

        Optional<Map<String, List<Action>>> authorizedUri = AuthorizationUtil.AUTHORIZATION_MAP.get(userRole)
                .stream()
                .filter(item -> item.containsKey(requestURI))
                .findFirst();

        boolean authorized = authorizedUri.isPresent() && authorizedUri.get().get(requestURI).contains(action);

        if (!authorized) {
            throw new AccessDeniedException(msa.getMessage("exception.access.denied"));
        }
    }
}
