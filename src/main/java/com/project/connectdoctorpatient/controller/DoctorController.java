package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.model.Doctor;
import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.helper.DoctorHelper;
import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.service.AccessManager;
import com.project.connectdoctorpatient.service.DoctorService;
import com.project.connectdoctorpatient.validator.DoctorValidator;
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
import static com.project.connectdoctorpatient.controller.DoctorController.DOCTOR_CMD;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@Controller
@RequestMapping("/doctor")
@SessionAttributes(DOCTOR_CMD)
public class DoctorController {

    public static final String DOCTOR_CMD = "doctor";

    private static final String VIEW_PAGE = "doctor/doctor";

    private final DoctorService doctorService;

    private final DoctorHelper doctorHelper;

    private final AccessManager accessManager;

    private final DoctorValidator doctorValidator;

    public DoctorController(DoctorService doctorService,
                            DoctorHelper doctorHelper,
                            AccessManager accessManager,
                            DoctorValidator doctorValidator) {

        this.doctorService = doctorService;
        this.doctorHelper = doctorHelper;
        this.accessManager = accessManager;
        this.doctorValidator = doctorValidator;
    }

    @InitBinder(DOCTOR_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(doctorValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long doctorId,
                       ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(SHOW);

        doctorHelper.setupReferenceData(doctorId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(DOCTOR_CMD) Doctor doctor,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(action);

        if (errors.hasErrors()) {
            doctorHelper.setupGenders(model);

            return VIEW_PAGE;
        }

        doctorService.performAction(action, doctor);

        sessionStatus.setComplete();

        doctorHelper.setupSuccessMessage(action, redirectAttributes);

        return RedirectUtil.redirect(SUCCESS_PAGE_URL);
    }
}
