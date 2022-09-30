package com.project.connectdoctorpatient.validator;

import com.project.connectdoctorpatient.util.RequestUtil;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.service.PatientService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/28/22
 */
@Component
public class PatientValidator implements Validator {

    protected final MessageSourceAccessor msa;

    private final PatientService patientService;

    public PatientValidator(MessageSourceAccessor msa, PatientService patientService) {
        this.msa = msa;
        this.patientService = patientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        Patient patient = (Patient) target;

        validateAction(action, patient, errors);
    }

    private void validateAction(Action action, Patient patient, Errors errors) {
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            validateForSaveOrUpdate(patient, errors);
        } else if (action.equals(DELETE)) {
            checkIfExists(patient, errors);
        }
    }

    private void validateForSaveOrUpdate(Patient patient, Errors errors) {
        if (patient.isNew()) {
            checkDuplicate(patient, errors);
        } else {
            checkIfExists(patient, errors);
        }
    }

    private void checkDuplicate(Patient patient, Errors errors) {
        if (nonNull(patientService.findByEmailAndPhoneNo(patient))) {
            errors.rejectValue(
                    msa.getMessage("error.field.password"),
                    msa.getMessage("error.patient"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(Patient patient, Errors errors) {
        if (isNull(patientService.findById(patient.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.name"),
                    msa.getMessage("error.course"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}
