package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.helper.PatientHelper;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Patient;
import com.project.connectdoctorpatient.service.AccessManager;
import com.project.connectdoctorpatient.service.PatientService;
import com.project.connectdoctorpatient.validator.PatientValidator;
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
import static com.project.connectdoctorpatient.controller.PatientController.PATIENT_CMD;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@Controller
@RequestMapping("/patient")
@SessionAttributes(PATIENT_CMD)
public class PatientController {

    public static final String PATIENT_CMD = "patient";

    private static final String VIEW_PAGE = "patient/patient";

    private final PatientService patientService;

    private final PatientHelper patientHelper;

    private final PatientValidator patientValidator;

    private final AccessManager accessManager;

    public PatientController(PatientService patientService,
                             PatientHelper patientHelper,
                             PatientValidator patientValidator,
                             AccessManager accessManager) {

        this.patientService = patientService;
        this.patientHelper = patientHelper;
        this.patientValidator = patientValidator;
        this.accessManager = accessManager;
    }

    @InitBinder(PATIENT_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(patientValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long patientId,
                       ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(SHOW);

        patientHelper.setupReferenceData(patientId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(PATIENT_CMD) Patient patient,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(action);

        if (errors.hasErrors()) {
            patientHelper.setupGenders(model);

            return VIEW_PAGE;
        }

        patientService.performAction(action, patient);

        sessionStatus.setComplete();

        patientHelper.setupSuccessMessage(action, redirectAttributes);

        return RedirectUtil.redirect(SUCCESS_PAGE_URL);
    }
}
