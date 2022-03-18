package com.sakib.connectdoctorpatient.controller;

import com.sakib.connectdoctorpatient.helper.IssueHelper;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Category;
import com.sakib.connectdoctorpatient.model.Issue;
import com.sakib.connectdoctorpatient.model.Status;
import com.sakib.connectdoctorpatient.service.IssueService;
import com.sakib.connectdoctorpatient.util.RedirectUtil;
import com.sakib.connectdoctorpatient.exception.AccessDeniedException;
import com.sakib.connectdoctorpatient.exception.NotFoundException;
import com.sakib.connectdoctorpatient.service.AccessManager;
import com.sakib.connectdoctorpatient.validator.IssueValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.sakib.connectdoctorpatient.constant.URL.*;
import static com.sakib.connectdoctorpatient.controller.IssueController.ISSUE_CMD;

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

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueHelper issueHelper;

    @Autowired
    private AccessManager accessManager;

    @Autowired
    private IssueValidator issueValidator;

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

        accessManager.checkAccess(Action.SHOW);

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

        accessManager.checkAccess(Action.SHOW);

        issueHelper.setupList(status, category, searchText, model);

        return LIST_PAGE;
    }
}
