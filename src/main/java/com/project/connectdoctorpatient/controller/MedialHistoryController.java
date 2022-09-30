package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.MedicalHistory;
import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.helper.MedicalHistoryHelper;
import com.project.connectdoctorpatient.exception.AccessDeniedException;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.service.AccessManager;
import com.project.connectdoctorpatient.service.MedicalHistoryService;
import com.project.connectdoctorpatient.validator.MedicalHistoryValidator;
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
import static com.project.connectdoctorpatient.controller.MedialHistoryController.MEDICAL_HISTORY_CMD;
import static com.project.connectdoctorpatient.model.Action.*;

/**
 * @author sakib.khan
 * @since 3/2/22
 */
@Controller
@RequestMapping("/medicalHistory")
@SessionAttributes(MEDICAL_HISTORY_CMD)
public class MedialHistoryController {

    public static final String MEDICAL_HISTORY_CMD = "medicalHistory";

    private static final String VIEW_PAGE = "medicalHistory/medicalHistory";
    private static final String LIST_PAGE = "medicalHistory/medicalHistoryList";

    private final MedicalHistoryService medicalHistoryService;

    private final MedicalHistoryHelper medicalHistoryHelper;

    private final AccessManager accessManager;

    private final MedicalHistoryValidator medicalHistoryValidator;

    public MedialHistoryController(MedicalHistoryService medicalHistoryService,
                                   MedicalHistoryHelper medicalHistoryHelper,
                                   AccessManager accessManager,
                                   MedicalHistoryValidator medicalHistoryValidator) {

        this.medicalHistoryService = medicalHistoryService;
        this.medicalHistoryHelper = medicalHistoryHelper;
        this.accessManager = accessManager;
        this.medicalHistoryValidator = medicalHistoryValidator;
    }

    @InitBinder(MEDICAL_HISTORY_CMD)
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.addValidators(medicalHistoryValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping
    public String show(@RequestParam(defaultValue = "0") long medicalHistoryId,
                       ModelMap model) throws AccessDeniedException, NotFoundException {

        accessManager.checkAccess(SHOW);

        medicalHistoryHelper.setupReferenceData(medicalHistoryId, model);

        return VIEW_PAGE;
    }

    @PostMapping
    public String process(@Valid @ModelAttribute(MEDICAL_HISTORY_CMD) MedicalHistory medicalHistory,
                          Errors errors,
                          @RequestParam Action action,
                          SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(action);

        if (errors.hasErrors()) {
            medicalHistoryHelper.setupCategory(model);

            return VIEW_PAGE;
        }

        medicalHistoryService.performAction(action, medicalHistory);

        sessionStatus.setComplete();

        medicalHistoryHelper.setupSuccessMessage(action, redirectAttributes);

        return RedirectUtil.redirect(SUCCESS_PAGE_URL);
    }

    @GetMapping("/list")
    public String showAll(@RequestParam(defaultValue = "0") long patientId,
                          ModelMap model) throws AccessDeniedException {

        accessManager.checkAccess(SHOW);

        medicalHistoryHelper.setupList(patientId, model);

        return LIST_PAGE;
    }
}
