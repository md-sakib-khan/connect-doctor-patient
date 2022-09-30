package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.PatientController;
import com.project.connectdoctorpatient.service.PatientService;
import com.project.connectdoctorpatient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class PatientHelper extends UserHelper {

    @Autowired
    private PatientService patientService;

    public void setupReferenceData(long patientId, ModelMap model) {
        model.addAttribute(PatientController.PATIENT_CMD, patientId == 0 ? new Patient() : patientService.findById(patientId));
        setupGenders(model);
    }
}
