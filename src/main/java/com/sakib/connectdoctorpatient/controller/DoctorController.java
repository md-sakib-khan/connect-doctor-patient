package com.sakib.connectdoctorpatient.controller;

import com.sakib.connectdoctorpatient.helper.DoctorHelper;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.Doctor;
import com.sakib.connectdoctorpatient.util.RedirectUtil;
import com.sakib.connectdoctorpatient.exception.AccessDeniedException;
import com.sakib.connectdoctorpatient.service.AccessManager;
import com.sakib.connectdoctorpatient.service.DoctorService;
import com.sakib.connectdoctorpatient.validator.DoctorValidator;
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
import static com.sakib.connectdoctorpatient.controller.DoctorController.DOCTOR_CMD;

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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorHelper doctorHelper;

    @Autowired
    private AccessManager accessManager;

    @Autowired
    private DoctorValidator doctorValidator;

    @InitBinder(DOCTOR_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(doctorValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long doctorId,
                       ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(Action.SHOW);

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
