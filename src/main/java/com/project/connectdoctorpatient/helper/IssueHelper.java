package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.IssueController;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.model.*;
import com.project.connectdoctorpatient.service.ExpertiseService;
import com.project.connectdoctorpatient.service.IssueService;
import com.project.connectdoctorpatient.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static com.project.connectdoctorpatient.model.Role.DOCTOR;
import static com.project.connectdoctorpatient.model.Role.PATIENT;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class IssueHelper {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private IssueService issueService;

    @Autowired
    private ExpertiseService expertiseService;

    public void setupReferenceData(long issueId, ModelMap model) throws NotFoundException {
        model.addAttribute(IssueController.ISSUE_CMD, getIssue(issueId));
        setupCategory(model);
    }

    public void setupCategory(ModelMap model) {
        model.addAttribute("categories", Category.values());
    }

    public void setupList(Status status, Category category, String searchText, ModelMap model) {
        List<Issue> issues = new ArrayList<>();

        User user = SessionUtil.getUser();

        if (user.getRole() == PATIENT) {
            issues = getListForPatient((Patient) user, status, category, searchText);
        } else if (user.getRole() == DOCTOR) {
            issues = getListForDoctor((Doctor) user, status, category, searchText);
        }

        model.addAttribute("issues", issues);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("categories", Category.values());
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        ra.addFlashAttribute("message", msa.getMessage("success." + action));
    }

    private List<Issue> getListForPatient(Patient patient, Status status, Category category, String problemStatement) {
        return issueService.findByPatentAndStatusAndStatement(patient, status, category, problemStatement);
    }

    private List<Issue> getListForDoctor(Doctor doctor, Status status, Category category, String problemStatement) {
        List<Category> categories = expertiseService.findByDoctor(doctor)
                .stream()
                .map(expertise -> expertise.getCategory())
                .collect(toList());

        return issueService.findByCategoriesAndStatusAndStatement(categories, status, category, problemStatement);
    }

    private Issue getIssue(long issueId) throws NotFoundException {
        if (issueId == 0) {
            return new Issue();
        }

        Issue issue = issueService.findById(issueId);

        checkNull(issue, issueId);

        return issue;
    }

    private void checkNull(Issue issue, long issueId) throws NotFoundException {
        if (isNull(issue)) {
            String message = msa.getMessage("exception.issue.invalid");
            message = message.replace("{id}", String.valueOf(issueId));
            throw new NotFoundException(message);
        }
    }
}
