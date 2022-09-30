package com.project.connectdoctorpatient.model;

/**
 * @author sakib.khan
 * @since 2/28/22
 */
public enum Status {

    PENDING("Pending"),
    RESPONDED("Responded");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
