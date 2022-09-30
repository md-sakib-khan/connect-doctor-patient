package com.project.connectdoctorpatient.validator;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Appointment;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.service.AppointmentService;
import com.project.connectdoctorpatient.service.DoctorService;
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
 * @since 3/6/22
 */
@Component
public class AppointmentValidator implements Validator {

    private final MessageSourceAccessor msa;

    private final AppointmentService appointmentService;

    private final DoctorService doctorService;

    public AppointmentValidator(MessageSourceAccessor msa,
                                AppointmentService appointmentService,
                                DoctorService doctorService) {

        this.msa = msa;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

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
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            validateForSaveOrUpdate(appointment, errors);
        } else if (action.equals(DELETE)) {
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

