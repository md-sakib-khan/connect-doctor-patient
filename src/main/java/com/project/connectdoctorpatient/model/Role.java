package com.project.connectdoctorpatient.model;

import java.util.List;
import java.util.Map;

import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.model.Action.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
public enum Role {

    PATIENT("Patient", asList(
            singletonMap(PATIENT_URL, asList(SHOW, UPDATE, DELETE)),
            singletonMap(MEDICAL_HISTORY_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(MEDICAL_HISTORY_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(ISSUE_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(ISSUE_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(DOCTOR_URL, singletonList(SHOW)),
            singletonMap(APPOINTMENT_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(APPOINTMENT_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)))
    ),

    DOCTOR("Doctor", asList(
            singletonMap(DOCTOR_URL, asList(SHOW, UPDATE, DELETE)),
            singletonMap(MEDICAL_HISTORY_URL, singletonList(SHOW)),
            singletonMap(MEDICAL_HISTORY_LIST_URL, singletonList(SHOW)),
            singletonMap(ISSUE_URL, asList(SHOW, UPDATE)),
            singletonMap(ISSUE_LIST_URL, singletonList(SHOW)),
            singletonMap(EXPERTISE_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(EXPERTISE_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(PATIENT_URL, singletonList(SHOW)),
            singletonMap(APPOINTMENT_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
            singletonMap(APPOINTMENT_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)))
    ),

    GUEST("Guest", asList(
            singletonMap(PATIENT_URL, asList(SHOW, SAVE)),
            singletonMap(DOCTOR_URL, asList(SHOW, SAVE)))
    );

    private final String displayName;
    private final List<Map<String, List<Action>>> permissions;

    Role(String displayName, List<Map<String, List<Action>>> permissions) {
        this.displayName = displayName;
        this.permissions = permissions;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Map<String, List<Action>>> getPermissions() {
        return permissions;
    }
}
