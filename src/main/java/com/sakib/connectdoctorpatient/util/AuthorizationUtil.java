package com.sakib.connectdoctorpatient.util;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Role;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static com.sakib.connectdoctorpatient.constant.URL.*;

/**
 * @author sakib.khan
 * @since 3/2/22
 */
public class AuthorizationUtil {

    public static final Map<Role, List<Map<String, List<Action>>>> AUTHORIZATION_MAP =
            new HashMap<Role, List<Map<String, List<Action>>>>() {{
                put(Role.PATIENT, asList(
                        singletonMap(PATIENT_URL, asList(Action.SHOW, Action.UPDATE, Action.DELETE)),
                        singletonMap(MEDICAL_HISTORY_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(MEDICAL_HISTORY_LIST_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(ISSUE_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(ISSUE_LIST_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(DOCTOR_URL, singletonList(Action.SHOW)),
                        singletonMap(APPOINTMENT_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(APPOINTMENT_LIST_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE))
                ));
                put(Role.DOCTOR, asList(
                        singletonMap(DOCTOR_URL, asList(Action.SHOW, Action.UPDATE, Action.DELETE)),
                        singletonMap(MEDICAL_HISTORY_URL, singletonList(Action.SHOW)),
                        singletonMap(MEDICAL_HISTORY_LIST_URL, singletonList(Action.SHOW)),
                        singletonMap(ISSUE_URL, asList(Action.SHOW, Action.UPDATE)),
                        singletonMap(ISSUE_LIST_URL, singletonList(Action.SHOW)),
                        singletonMap(EXPERTISE_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(EXPERTISE_LIST_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(PATIENT_URL, singletonList(Action.SHOW)),
                        singletonMap(APPOINTMENT_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE)),
                        singletonMap(APPOINTMENT_LIST_URL, asList(Action.SHOW, Action.SAVE, Action.UPDATE, Action.DELETE))
                ));
                put(Role.GUEST, asList(
                        singletonMap(PATIENT_URL, asList(Action.SHOW, Action.SAVE)),
                        singletonMap(DOCTOR_URL, asList(Action.SHOW, Action.SAVE))
                ));
            }};
}
