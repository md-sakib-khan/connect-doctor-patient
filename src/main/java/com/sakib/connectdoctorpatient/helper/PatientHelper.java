package com.sakib.connectdoctorpatient.helper;

import com.sakib.connectdoctorpatient.model.Patient;
import com.sakib.connectdoctorpatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static com.sakib.connectdoctorpatient.controller.PatientController.PATIENT_CMD;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class PatientHelper extends UserHelper {

    @Autowired
    private PatientService patientService;

    public void setupReferenceData(long patientId, ModelMap model) {
        model.addAttribute(PATIENT_CMD, patientId == 0 ? new Patient() : patientService.findById(patientId));
        setupGenders(model);
    }
}
