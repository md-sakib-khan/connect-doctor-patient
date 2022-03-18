package com.sakib.connectdoctorpatient.validator;

import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Issue;
import com.sakib.connectdoctorpatient.model.Patient;
import com.sakib.connectdoctorpatient.service.IssueService;
import com.sakib.connectdoctorpatient.util.RequestUtil;
import com.sakib.connectdoctorpatient.util.SessionUtil;
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
 * @since 3/4/22
 */
@Component
public class IssueValidator implements Validator {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private IssueService issueService;

    @Autowired
    private PatientService patientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Issue.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Action action = Action.valueOf(RequestUtil.getRequest().getParameter("action"));
        Issue issue = (Issue) target;

        validateAction(action, issue, errors);
    }

    private void validateAction(Action action, Issue issue, Errors errors) {
        if (action.equals(Action.SAVE) || action.equals(Action.UPDATE)) {
            validateForSaveOrUpdate(issue, errors);
        } else if (action.equals(Action.DELETE)) {
            checkIfExists(issue, errors);
        }
    }

    private void validateForSaveOrUpdate(Issue issue, Errors errors) {
        if (issue.isNew()) {
            checkDuplicate(issue, errors);
        } else {
            checkIfExists(issue, errors);
        }
    }

    private void checkDuplicate(Issue issue, Errors errors) {
        Patient patient = patientService.findById(SessionUtil.getUser().getId());

        if (nonNull(issueService.findByPropertiesAndPatient(issue, patient))) {
            errors.rejectValue(
                    msa.getMessage("error.field.date.start"),
                    msa.getMessage("error.issue"),
                    msa.getMessage("error.exist.true")
            );
        }
    }

    private void checkIfExists(Issue issue, Errors errors) {
        if (isNull(issueService.findById(issue.getId()))) {
            errors.rejectValue(
                    msa.getMessage("error.field.date.start"),
                    msa.getMessage("error.issue"),
                    msa.getMessage("error.exist.false")
            );
        }
    }
}
