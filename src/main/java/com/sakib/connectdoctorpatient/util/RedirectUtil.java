package com.sakib.connectdoctorpatient.util;

import static com.sakib.connectdoctorpatient.constant.URL.REDIRECT;

/**
 * @author sakib.khan
 * @since 3/15/22
 */
public class RedirectUtil {

    public static String redirect(String url) {
        return REDIRECT + url;
    }
}
