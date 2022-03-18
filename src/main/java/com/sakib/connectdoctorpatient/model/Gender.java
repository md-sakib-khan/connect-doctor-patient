package com.sakib.connectdoctorpatient.model;

/**
 * @author sakib.khan
 * @since 2/28/22
 */
public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
