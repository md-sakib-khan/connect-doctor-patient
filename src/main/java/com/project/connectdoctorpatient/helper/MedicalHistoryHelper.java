package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.MedialHistoryController;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.model.*;
import com.project.connectdoctorpatient.service.MedicalHistoryService;
import com.project.connectdoctorpatient.service.PatientService;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static com.project.connectdoctorpatient.model.Role.PATIENT;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class MedicalHistoryHelper {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    public void setupReferenceData(long medicalHistoryId, ModelMap model) throws NotFoundException {
        model.addAttribute(MedialHistoryController.MEDICAL_HISTORY_CMD, getMedicalHistory(medicalHistoryId));
        setupCategory(model);
    }

    public void setupCategory(ModelMap model) {
        model.addAttribute("categories", Category.values());
    }

    public void setupList(long patientId, ModelMap model) {
        model.addAttribute("medicalHistories", getMedicalHistories(SessionUtil.getUser(), patientId));
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        ra.addFlashAttribute("message", msa.getMessage("success." + action));
    }

    private MedicalHistory getMedicalHistory(long medicalHistoryId) throws NotFoundException {
        if (medicalHistoryId == 0) {
            return new MedicalHistory();
        }

        MedicalHistory medicalHistory = medicalHistoryService.findById(medicalHistoryId);

        checkNull(medicalHistory, medicalHistoryId);

        return medicalHistory;
    }

    private List<MedicalHistory> getMedicalHistories(User user, long patientId) {
        List<MedicalHistory> medicalHistories = new ArrayList<>();

        if (patientId != 0) {
            Patient patient = patientService.findById(patientId);
            medicalHistories = medicalHistoryService.findByPatient(patient);
        } else if (user.getRole() == PATIENT) {
            medicalHistories = medicalHistoryService.findByPatient((Patient) user);
        }

        return medicalHistories;
    }

    private void checkNull(MedicalHistory medicalHistory, long medicalHistoryId) throws NotFoundException {
        if (isNull(medicalHistory)) {
            String message = msa.getMessage("exception.medical.history.invalid");
            message = message.replace("{id}", String.valueOf(medicalHistoryId));
            throw new NotFoundException(message);
        }
    }
}
