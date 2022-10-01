package com.project.connectdoctorpatient.service;

import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.util.RequestUtil;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import static com.project.connectdoctorpatient.util.SessionUtil.getUserRole;

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

        boolean authorized = getUserRole().getPermissions()
                .stream()
                .anyMatch(uriMap -> uriMap.containsKey(requestURI) && uriMap.get(requestURI).contains(action));

        if (!authorized) {
            throw new AccessDeniedException(msa.getMessage("exception.access.denied"));
        }
    }
}
