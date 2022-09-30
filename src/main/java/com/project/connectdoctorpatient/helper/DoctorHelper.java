package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.DoctorController;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.service.DoctorService;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class DoctorHelper {

    private final DoctorService doctorService;

    private final UserHelper userHelper;

    public DoctorHelper(DoctorService doctorService, UserHelper userHelper) {
        this.doctorService = doctorService;
        this.userHelper = userHelper;
    }

    public void setupGenders(ModelMap model) {
        userHelper.setupGenders(model);
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        userHelper.setupSuccessMessage(action, ra);
    }

    public void setupReferenceData(long doctorId, ModelMap model) {
        model.addAttribute(DoctorController.DOCTOR_CMD, doctorId == 0 ? new Doctor() : doctorService.findById(doctorId));
        setupGenders(model);
    }
}
