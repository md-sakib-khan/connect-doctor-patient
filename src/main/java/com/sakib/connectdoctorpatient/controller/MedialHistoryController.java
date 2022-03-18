package com.sakib.connectdoctorpatient.controller;

import com.sakib.connectdoctorpatient.helper.MedicalHistoryHelper;
import com.sakib.connectdoctorpatient.model.Action;
import com.sakib.connectdoctorpatient.model.MedicalHistory;
import com.sakib.connectdoctorpatient.util.RedirectUtil;
import com.sakib.connectdoctorpatient.exception.AccessDeniedException;
import com.sakib.connectdoctorpatient.exception.NotFoundException;
import com.sakib.connectdoctorpatient.service.AccessManager;
import com.sakib.connectdoctorpatient.service.MedicalHistoryService;
import com.sakib.connectdoctorpatient.validator.MedicalHistoryValidator;
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
import static com.sakib.connectdoctorpatient.controller.MedialHistoryController.MEDICAL_HISTORY_CMD;

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

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private MedicalHistoryHelper medicalHistoryHelper;

    @Autowired
    private AccessManager accessManager;

    @Autowired
    private MedicalHistoryValidator medicalHistoryValidator;

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

        accessManager.checkAccess(Action.SHOW);

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

        accessManager.checkAccess(Action.SHOW);

        medicalHistoryHelper.setupList(patientId, model);

        return LIST_PAGE;
    }
}
