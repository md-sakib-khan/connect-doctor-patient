package com.sakib.connectdoctorpatient.helper;

import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static com.sakib.connectdoctorpatient.controller.DoctorController.DOCTOR_CMD;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class DoctorHelper extends UserHelper {

    @Autowired
    private DoctorService doctorService;

    public void setupReferenceData(long doctorId, ModelMap model) {
        model.addAttribute(DOCTOR_CMD, doctorId == 0 ? new Doctor() : doctorService.findById(doctorId));
        setupGenders(model);
    }
}
