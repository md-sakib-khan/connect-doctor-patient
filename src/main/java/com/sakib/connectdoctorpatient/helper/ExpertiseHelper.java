package com.sakib.connectdoctorpatient.helper;

import com.sakib.connectdoctorpatient.model.*;
import com.sakib.connectdoctorpatient.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.sakib.connectdoctorpatient.controller.ExpertiseController.EXPERTISE_CMD;
import static com.sakib.connectdoctorpatient.model.Role.DOCTOR;
import static com.sakib.connectdoctorpatient.util.SessionUtil.getUser;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class ExpertiseHelper {

    @Autowired
    private MessageSourceAccessor msa;

    @Autowired
    private ExpertiseService expertiseService;

    public void setupReferenceData(long expertiseId, ModelMap model) {
        model.addAttribute(EXPERTISE_CMD, expertiseId == 0 ? new Expertise() : expertiseService.findById(expertiseId));
        setupCategory(model);
    }

    public void setupCategory(ModelMap model) {
        model.addAttribute("categories", Category.values());
    }

    public void setupList(ModelMap model) {
        User user = getUser();

        if (user.getRole() == DOCTOR) {
            model.addAttribute("expertiseList", expertiseService.findByDoctor((Doctor) user));
        }
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        ra.addFlashAttribute("message", msa.getMessage("success." + action));
    }
}
