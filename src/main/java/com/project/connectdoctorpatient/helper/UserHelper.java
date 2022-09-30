package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.model.Action;
import com.project.connectdoctorpatient.model.Gender;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.project.connectdoctorpatient.model.Action.SAVE;

/**
 * @author sakib.khan
 * @since 3/5/22
 */
@Component
public class UserHelper {

    private final MessageSourceAccessor msa;

    public UserHelper(MessageSourceAccessor msa) {
        this.msa = msa;
    }

    public void setupGenders(ModelMap model) {
        model.addAttribute("genders", Gender.values());
    }

    public void setupSuccessMessage(Action action, RedirectAttributes ra) {
        if (SAVE.equals(action)) {
            ra.addFlashAttribute("registerMessage", msa.getMessage("success.REGISTER"));
            return;
        }

        ra.addFlashAttribute("message", msa.getMessage("success." + action));
    }
}
