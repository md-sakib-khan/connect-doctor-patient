package com.project.connectdoctorpatient.model;

import java.util.List;
import java.util.Map;

import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
public enum Role {

    PATIENT("Patient", Map.of(
            PATIENT_URL, List.of(SHOW, UPDATE, DELETE),
            MEDICAL_HISTORY_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            MEDICAL_HISTORY_LIST_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            ISSUE_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            ISSUE_LIST_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            DOCTOR_URL, List.of(SHOW),
            APPOINTMENT_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            APPOINTMENT_LIST_URL, List.of(SHOW, SAVE, UPDATE, DELETE))
    ),

    DOCTOR("Doctor", Map.of(
            DOCTOR_URL, List.of(SHOW, UPDATE, DELETE),
            MEDICAL_HISTORY_URL, List.of(SHOW),
            MEDICAL_HISTORY_LIST_URL, List.of(SHOW),
            ISSUE_URL, List.of(SHOW, UPDATE),
            ISSUE_LIST_URL, List.of(SHOW),
            EXPERTISE_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            EXPERTISE_LIST_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            PATIENT_URL, List.of(SHOW),
            APPOINTMENT_URL, List.of(SHOW, SAVE, UPDATE, DELETE),
            APPOINTMENT_LIST_URL, List.of(SHOW, SAVE, UPDATE, DELETE))
    ),

    GUEST("Guest", Map.of(
            PATIENT_URL, List.of(SHOW, SAVE),
            DOCTOR_URL, List.of(SHOW, SAVE))
    );

    private final String displayName;
    private final Map<String, List<Action>> permissions;

    Role(String displayName, Map<String, List<Action>> permissions) {
        this.displayName = displayName;
        this.permissions = permissions;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Map<String, List<Action>> getPermissions() {
        return permissions;
    }
}
