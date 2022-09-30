package com.project.connectdoctorpatient.util;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Role;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.model.Action.*;
import static com.project.connectdoctorpatient.model.Role.*;

/**
 * @author sakib.khan
 * @since 3/2/22
 */
public class AuthorizationUtil {

    public static final Map<Role, List<Map<String, List<Action>>>> AUTHORIZATION_MAP =
            new HashMap<Role, List<Map<String, List<Action>>>>() {{
                put(PATIENT, asList(
                        singletonMap(PATIENT_URL, asList(SHOW, UPDATE, DELETE)),
                        singletonMap(MEDICAL_HISTORY_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(MEDICAL_HISTORY_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(ISSUE_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(ISSUE_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(DOCTOR_URL, singletonList(SHOW)),
                        singletonMap(APPOINTMENT_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(APPOINTMENT_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE))
                ));
                put(DOCTOR, asList(
                        singletonMap(DOCTOR_URL, asList(SHOW, UPDATE, DELETE)),
                        singletonMap(MEDICAL_HISTORY_URL, singletonList(SHOW)),
                        singletonMap(MEDICAL_HISTORY_LIST_URL, singletonList(SHOW)),
                        singletonMap(ISSUE_URL, asList(SHOW, UPDATE)),
                        singletonMap(ISSUE_LIST_URL, singletonList(SHOW)),
                        singletonMap(EXPERTISE_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(EXPERTISE_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(PATIENT_URL, singletonList(SHOW)),
                        singletonMap(APPOINTMENT_URL, asList(SHOW, SAVE, UPDATE, DELETE)),
                        singletonMap(APPOINTMENT_LIST_URL, asList(SHOW, SAVE, UPDATE, DELETE))
                ));
                put(GUEST, asList(
                        singletonMap(PATIENT_URL, asList(SHOW, SAVE)),
                        singletonMap(DOCTOR_URL, asList(SHOW, SAVE))
                ));
            }};
}
