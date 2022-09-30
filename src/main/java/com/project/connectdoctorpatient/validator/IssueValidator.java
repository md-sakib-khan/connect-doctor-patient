package com.project.connectdoctorpatient.validator;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Issue;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.service.IssueService;
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
 * @since 3/4/22
 */
@Component
public class IssueValidator implements Validator {

    private final MessageSourceAccessor msa;

    private final IssueService issueService;

    private final PatientService patientService;

    public IssueValidator(MessageSourceAccessor msa, IssueService issueService, PatientService patientService) {
        this.msa = msa;
        this.issueService = issueService;
        this.patientService = patientService;
    }

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
        if (action.equals(SAVE) || action.equals(UPDATE)) {
            validateForSaveOrUpdate(issue, errors);
        } else if (action.equals(DELETE)) {
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
