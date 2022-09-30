package com.project.connectdoctorpatient.validator;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.MedicalHistory;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.service.MedicalHistoryService;
import com.project.connectdoctorpatient.service.PatientService;
import com.project.connectdoctorpatient.util.RequestUtil;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.project.connectdoctorpatient.model.Action.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/3/22
 */
@Component
public class MedicalHistoryValidator implements Validator {

    private final MedicalHistoryService medicalHistoryService;

    private final MessageSourceAccessor msa;

    private final PatientService patientService;

    public MedicalHistoryValidator(MedicalHistoryService medicalHistoryService,
                                   MessageSourceAccessor msa,
                                   PatientService patientService) {

        this.medicalHistoryService = medicalHistoryService;
        this.msa = msa;
        this.patientService = patientService;
    }

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
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            validateForSaveOrUpdate(medicalHistory, errors);
        } else if (action.equals(DELETE)) {
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
