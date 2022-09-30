package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.helper.ExpertiseHelper;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Expertise;
import com.project.connectdoctorpatient.service.AccessManager;
import com.project.connectdoctorpatient.service.ExpertiseService;
import com.project.connectdoctorpatient.validator.ExpertiseValidator;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.controller.ExpertiseController.EXPERTISE_CMD;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Controller
@RequestMapping("/expertise")
@SessionAttributes(EXPERTISE_CMD)
public class ExpertiseController {

    public static final String EXPERTISE_CMD = "expertise";

    private static final String VIEW_PAGE = "expertise/expertise";
    private static final String LIST_PAGE = "expertise/expertiseList";

    private final ExpertiseService expertiseService;

    private final ExpertiseHelper expertiseHelper;

    private final AccessManager accessManager;

    private final ExpertiseValidator expertiseValidator;

    public ExpertiseController(ExpertiseService expertiseService,
                               ExpertiseHelper expertiseHelper,
                               AccessManager accessManager,
                               ExpertiseValidator expertiseValidator) {

        this.expertiseService = expertiseService;
        this.expertiseHelper = expertiseHelper;
        this.accessManager = accessManager;
        this.expertiseValidator = expertiseValidator;
    }

    @InitBinder(EXPERTISE_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(expertiseValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long expertiseId,
                       ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(SHOW);

        expertiseHelper.setupReferenceData(expertiseId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(EXPERTISE_CMD) Expertise expertise,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(action);

        if (errors.hasErrors()) {
            expertiseHelper.setupCategory(model);

            return VIEW_PAGE;
        }

        expertiseService.performAction(action, expertise);

        sessionStatus.setComplete();

        expertiseHelper.setupSuccessMessage(action, redirectAttributes);

        return RedirectUtil.redirect(SUCCESS_PAGE_URL);
    }

    @GetMapping("/list")
    public String showAll(ModelMap model) throws AccessDeniedException {
        accessManager.checkAccess(SHOW);

        expertiseHelper.setupList(model);

        return LIST_PAGE;
    }
}
