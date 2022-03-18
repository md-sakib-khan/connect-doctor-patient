package com.sakib.connectdoctorpatient.model;

/**
 * @author sakib.khan
 * @since 2/24/22
 */
public enum Role {

    PATIENT("Patient"),
    DOCTOR("Doctor"),
    GUEST("Guest");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
