package com.sakib.connectdoctorpatient.validator;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.MedicalHistory;
import com.sakib.connectdoctorpatient.model.Patient;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.util.SessionUtil;
import com.sakib.connectdoctorpatient.service.MedicalHistoryService;
import com.sakib.connectdoctorpatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/3/22
 */
@Component
public class MedicalHistoryValidator implements Validator {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private PatientService patientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MedicalHistory.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        MedicalHistory medicalHistory = (MedicalHistory) target;

        validateAction(action, medicalHistory, errors);
    }

    private void validateAction(Action action, MedicalHistory medicalHistory, Errors errors) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            validateForSaveOrUpdate(medicalHistory, errors);
        } else if (action.equals(Action.DELETE)) {
            checkIfExists(medicalHistory, errors);
        }
    }

    private void validateForSaveOrUpdate(MedicalHistory medicalHistory, Errors errors) {
        if (medicalHistory.isNew()) {
            checkDuplicate(medicalHistory, errors);
        } else {
            checkIfExists(medicalHistory, errors);
        }
    }

    private void checkDuplicate(MedicalHistory medicalHistory, Errors errors) {
        Patient patient = patientService.findById(SessionUtil.getUser().getId());

        if (nonNull(medicalHistoryService.findByPropertiesAndPatient(medicalHistory, patient))) {
            errors.rejectValue(
                    msa.getMessage("error.field.doctor.comment"),
                    msa.getMessage("error.duplicate.code"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(MedicalHistory medicalHistory, Errors errors) {
        if (isNull(medicalHistoryService.findById(medicalHistory.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.doctor.comment"),
                    msa.getMessage("error.exists.code"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}
