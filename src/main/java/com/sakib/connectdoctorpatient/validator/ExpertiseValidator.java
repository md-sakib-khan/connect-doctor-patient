package com.sakib.connectdoctorpatient.validator;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.model.Expertise;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.util.SessionUtil;
import com.sakib.connectdoctorpatient.service.DoctorService;
import com.sakib.connectdoctorpatient.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class ExpertiseValidator implements Validator {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private ExpertiseService expertiseService;

    @Autowired
    private DoctorService doctorService;

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
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            validateForSaveOrUpdate(expertise, errors);
        } else if (action.equals(Action.DELETE)) {
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
