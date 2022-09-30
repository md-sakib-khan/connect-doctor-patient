package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.PatientController;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.service.PatientService;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class PatientHelper {

    private final PatientService patientService;

    private final UserHelper userHelper;

    public PatientHelper(PatientService patientService, UserHelper userHelper) {
        this.patientService = patientService;
        this.userHelper = userHelper;
    }

    public void setupGenders(ModelMap model) {
        userHelper.setupGenders(model);
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        userHelper.setupSuccessMessage(action, ra);
    }

    public void setupReferenceData(long patientId, ModelMap model) {
        model.addAttribute(PatientController.PATIENT_CMD, patientId == 0 ? new Patient() : patientService.findById(patientId));
        setupGenders(model);
    }
}
