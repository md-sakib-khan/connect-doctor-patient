package com.project.connectdoctorpatient.util;

import com.project.connectdoctorpatient.model.Role;
import com.project.connectdoctorpatient.model.User;

import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;
import static com.project.connectdoctorpatient.controller.AuthenticationController.LOGGED_USER_CMD;
import static com.project.connectdoctorpatient.model.Role.GUEST;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
public final class SessionUtil {

    private SessionUtil() {
        throw new AssertionError("Utility Class Constructor Called.");
    }

    public static HttpSession getSession() {
        return RequestUtil.getRequest().getSession();
    }

    public static Object getAttribute(String key) {
        return getSession().getAttribute(key);
    }

    public static void setAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static User getUser() {
        return (User) getAttribute(LOGGED_USER_CMD);
    }

    public static Role getUserRole() {
        return nonNull(getUser())? getUser().getRole() : GUEST;
    }
}
