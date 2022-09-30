package com.project.connectdoctorpatient.model;

/**
 * @author sakib.khan
 * @since 2/28/22
 */
public enum Category {

    HEART("Heart"),
    LUNGS("Lungs"),
    EYE("Eye"),
    EAR("Ear"),
    COLD("Cold");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
