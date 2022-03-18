package com.sakib.connectdoctorpatient.util;

import com.sakib.connectdoctorpatient.model.Role;
import com.sakib.connectdoctorpatient.model.User;

import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;
import static com.sakib.connectdoctorpatient.controller.AuthenticationController.LOGGED_USER_CMD;
import static com.sakib.connectdoctorpatient.util.RequestUtil.getRequest;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
public class SessionUtil {

    public static HttpSession getSession() {
        return getRequest().getSession();
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
        return nonNull(getUser())? getUser().getRole() : Role.GUEST;
    }
}
