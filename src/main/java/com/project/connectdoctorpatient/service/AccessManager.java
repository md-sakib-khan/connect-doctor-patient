package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Role;
import com.project.connectdoctorpatient.util.AuthorizationUtil;
import com.project.connectdoctorpatient.util.RequestUtil;
import com.project.connectdoctorpatient.util.SessionUtil;
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

    private final MessageSourceAccessor msa;

    public AccessManager(MessageSourceAccessor msa) {
        this.msa = msa;
    }

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
