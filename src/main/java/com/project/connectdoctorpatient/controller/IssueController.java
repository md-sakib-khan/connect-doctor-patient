package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Category;
import com.project.connectdoctorpatient.model.Issue;
import com.project.connectdoctorpatient.model.Status;
import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.helper.IssueHelper;
import com.project.connectdoctorpatient.service.AccessManager;
import com.project.connectdoctorpatient.service.IssueService;
import com.project.connectdoctorpatient.validator.IssueValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.controller.IssueController.ISSUE_CMD;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 3/2/22
 */
@Controller
@RequestMapping("/issue")
@SessionAttributes(ISSUE_CMD)
public class IssueController {

    public static final String ISSUE_CMD = "issue";

    private static final String VIEW_PAGE = "issue/issue";
    private static final String LIST_PAGE = "issue/issueList";

    private final IssueService issueService;

    private final IssueHelper issueHelper;

    private final AccessManager accessManager;

    private final IssueValidator issueValidator;

    public IssueController(IssueService issueService,
                           IssueHelper issueHelper,
                           AccessManager accessManager,
                           IssueValidator issueValidator) {

        this.issueService = issueService;
        this.issueHelper = issueHelper;
        this.accessManager = accessManager;
        this.issueValidator = issueValidator;
    }

    @InitBinder(ISSUE_CMD)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.addValidators(issueValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long issueId,
                       ModelMap model) throws AccessDeniedException, NotFoundException {

        accessManager.checkAccess(SHOW);

        issueHelper.setupReferenceData(issueId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(ISSUE_CMD) Issue issue,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(action);

        if (errors.hasErrors()) {
            issueHelper.setupCategory(model);

            return VIEW_PAGE;
        }

        issueService.performAction(action, issue);

        sessionStatus.setComplete();

        issueHelper.setupSuccessMessage(action, redirectAttributes);

        return RedirectUtil.redirect(SUCCESS_PAGE_URL);
    }

    @GetMapping("/list")
    public String showAll(@RequestParam(required = false) Status status,
                          @RequestParam(required = false) Category category,
                          @RequestParam(required = false) String searchText,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(SHOW);

        issueHelper.setupList(status, category, searchText, model);

        return LIST_PAGE;
    }
}
