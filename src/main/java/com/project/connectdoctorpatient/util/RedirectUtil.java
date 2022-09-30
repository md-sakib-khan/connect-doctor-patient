package com.project.connectdoctorpatient.util;

import static com.project.connectdoctorpatient.constant.URL.REDIRECT;

/**
 * @author sakib.khan
 * @since 3/15/22
 */
public class RedirectUtil {

    public static String redirect(String url) {
        return REDIRECT + url;
    }
}
