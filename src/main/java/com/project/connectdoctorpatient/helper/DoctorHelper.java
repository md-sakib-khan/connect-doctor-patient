package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.DoctorController;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class DoctorHelper extends UserHelper {

    @Autowired
    private DoctorService doctorService;

    public void setupReferenceData(long doctorId, ModelMap model) {
        model.addAttribute(DoctorController.DOCTOR_CMD, doctorId == 0 ? new Doctor() : doctorService.findById(doctorId));
        setupGenders(model);
    }
}
