package com.sakib.connectdoctorpatient.validator;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Appointment;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.util.SessionUtil;
import com.sakib.connectdoctorpatient.service.AppointmentService;
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
 * @since 3/6/22
 */
@Component
public class AppointmentValidator implements Validator {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Appointment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        Appointment appointment = (Appointment) target;

        validateAction(action, appointment, errors);
    }

    private void validateAction(Action action, Appointment appointment, Errors errors) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            validateForSaveOrUpdate(appointment, errors);
        } else if (action.equals(Action.DELETE)) {
            checkIfExists(appointment, errors);
        }
    }

    private void validateForSaveOrUpdate(Appointment appointment, Errors errors) {
        if (appointment.isNew()) {
            checkDuplicate(appointment, errors);
        } else {
            checkIfExists(appointment, errors);
        }
    }

    private void checkDuplicate(Appointment appointment, Errors errors) {
        Doctor doctor = doctorService.findById(SessionUtil.getUser().getId());

        if (nonNull(appointmentService.findByPropertiesAndDoctor(appointment, doctor))) {
            errors.rejectValue(
                    msa.getMessage("error.field.details"),
                    msa.getMessage("error.appointment"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(Appointment appointment, Errors errors) {
        if (isNull(appointmentService.findById(appointment.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.details"),
                    msa.getMessage("error.appointment"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}

