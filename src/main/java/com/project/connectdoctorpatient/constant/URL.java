package com.project.connectdoctorpatient.constant;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
public final class URL {

    private URL() {
        throw new AssertionError("Utility Class Constructor Called.");
    }

    public static final String REDIRECT = "redirect:";
    public static final String LOGIN_URL = "/login";
    public static final String DASHBOARD_URL = "/dashboard";
    public static final String PATIENT_URL = "/patient";
    public static final String PATIENT_LIST_URL = "/patient/list";
    public static final String DOCTOR_URL = "/doctor";
    public static final String DOCTOR_LIST_URL = "/doctor/list";
    public static final String MEDICAL_HISTORY_URL = "/medicalHistory";
    public static final String MEDICAL_HISTORY_LIST_URL = "/medicalHistory/list";
    public static final String ISSUE_URL = "/issue";
    public static final String ISSUE_LIST_URL = "/issue/list";
    public static final String APPOINTMENT_URL = "/appointment";
    public static final String APPOINTMENT_LIST_URL = "/appointment/list";
    public static final String EXPERTISE_URL = "/expertise";
    public static final String EXPERTISE_LIST_URL = "/expertise/list";
    public static final String SUCCESS_PAGE_URL = "/success";

    public static final String[] AUTHENTICATION_URLS = {
            DASHBOARD_URL,
            PATIENT_URL,
            PATIENT_LIST_URL,
            DOCTOR_URL,
            DOCTOR_LIST_URL,
            MEDICAL_HISTORY_URL,
            MEDICAL_HISTORY_LIST_URL,
            ISSUE_URL,
            ISSUE_LIST_URL,
            EXPERTISE_URL,
            EXPERTISE_LIST_URL,
            APPOINTMENT_URL,
            APPOINTMENT_LIST_URL
    };
}
