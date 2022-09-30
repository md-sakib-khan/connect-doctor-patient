package com.project.connectdoctorpatient.validator;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.model.Expertise;
import com.project.connectdoctorpatient.service.DoctorService;
import com.project.connectdoctorpatient.service.ExpertiseService;
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
 * @since 3/5/22
 */
@Component
public class ExpertiseValidator implements Validator {

    private final MessageSourceAccessor msa;

    private final ExpertiseService expertiseService;

    private final DoctorService doctorService;

    public ExpertiseValidator(MessageSourceAccessor msa, ExpertiseService expertiseService, DoctorService doctorService) {
        this.msa = msa;
        this.expertiseService = expertiseService;
        this.doctorService = doctorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Expertise.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        Expertise expertise = (Expertise) target;

        validateAction(action, expertise, errors);
    }

    private void validateAction(Action action, Expertise expertise, Errors errors) {
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            validateForSaveOrUpdate(expertise, errors);
        } else if (action.equals(DELETE)) {
            checkIfExists(expertise, errors);
        }
    }

    private void validateForSaveOrUpdate(Expertise expertise, Errors errors) {
        if (expertise.isNew()) {
            checkDuplicate(expertise, errors);
        } else {
            checkIfExists(expertise, errors);
        }
    }

    private void checkDuplicate(Expertise expertise, Errors errors) {
        Doctor doctor = doctorService.findById(SessionUtil.getUser().getId());

        if (nonNull(expertiseService.findByPropertiesAndDoctor(expertise, doctor))) {
            errors.rejectValue(
                    msa.getMessage("error.field.expertise.years"),
                    msa.getMessage("error.expertise"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(Expertise expertise, Errors errors) {
        if (isNull(expertiseService.findById(expertise.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.expertise.years"),
                    msa.getMessage("error.expertise"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}
