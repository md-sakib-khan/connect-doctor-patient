package com.sakib.connectdoctorpatient.validator;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 2/28/22
 */
@Component
public class DoctorValidator implements Validator {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private DoctorService doctorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Doctor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        Doctor doctor = (Doctor) target;

        validateAction(action, doctor, errors);
    }

    private void validateAction(Action action, Doctor doctor, Errors errors) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            validateForSaveOrUpdate(doctor, errors);
        } else if (action.equals(Action.DELETE)) {
            checkIfExists(doctor, errors);
        }
    }

    private void validateForSaveOrUpdate(Doctor doctor, Errors errors) {
        if (doctor.isNew()) {
            checkDuplicate(doctor, errors);
        } else {
            checkIfExists(doctor, errors);
        }
    }

    private void checkDuplicate(Doctor doctor, Errors errors) {
        if (nonNull(doctorService.findByEmailAndPhone(doctor))) {
            errors.rejectValue(
                    msa.getMessage("error.field.password"),
                    msa.getMessage("error.patient"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(Doctor doctor, Errors errors) {
        if (isNull(doctorService.findById(doctor.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.name"),
                    msa.getMessage("error.course"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}
