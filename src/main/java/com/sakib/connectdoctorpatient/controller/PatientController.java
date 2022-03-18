package com.sakib.connectdoctorpatient.controller;

import com.sakib.connectdoctorpatient.helper.PatientHelper;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Patient;
import com.sakib.connectdoctorpatient.util.RedirectUtil;
import com.sakib.connectdoctorpatient.exception.AccessDeniedException;
import com.sakib.connectdoctorpatient.service.AccessManager;
import com.sakib.connectdoctorpatient.service.PatientService;
import com.sakib.connectdoctorpatient.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.sakib.connectdoctorpatient.constant.URL.*;
import static com.sakib.connectdoctorpatient.controller.PatientController.PATIENT_CMD;

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

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientHelper patientHelper;

    @Autowired
    private PatientValidator patientValidator;

    @Autowired
    private AccessManager accessManager;

    @InitBinder(PATIENT_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(patientValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long patientId,
                       ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(Action.SHOW);

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
