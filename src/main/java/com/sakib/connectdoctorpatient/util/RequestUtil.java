package com.sakib.connectdoctorpatient.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.requireNonNull;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }
}