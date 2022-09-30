package com.project.connectdoctorpatient.constant;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
public interface URL {

    String REDIRECT = "redirect:";
    String LOGIN_URL = "/login";
    String DASHBOARD_URL = "/dashboard";
    String PATIENT_URL = "/patient";
    String PATIENT_LIST_URL = "/patient/list";
    String DOCTOR_URL = "/doctor";
    String DOCTOR_LIST_URL = "/doctor/list";
    String MEDICAL_HISTORY_URL = "/medicalHistory";
    String MEDICAL_HISTORY_LIST_URL = "/medicalHistory/list";
    String ISSUE_URL = "/issue";
    String ISSUE_LIST_URL = "/issue/list";
    String APPOINTMENT_URL = "/appointment";
    String APPOINTMENT_LIST_URL = "/appointment/list";
    String EXPERTISE_URL = "/expertise";
    String EXPERTISE_LIST_URL = "/expertise/list";
    String SUCCESS_PAGE_URL = "/success";

    String[] AUTHENTICATION_URLS = {
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
