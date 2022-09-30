package com.project.connectdoctorpatient.helper;

import com.project.connectdoctorpatient.controller.AuthenticationController;
import com.project.connectdoctorpatient.dto.LoginDTO;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * @author sakib.khan
 * @since 3/6/22
 */
@Component
public class AuthenticationHelper {

    public void setupCommand(ModelMap model) {
        model.addAttribute(AuthenticationController.LOGIN_CMD, new LoginDTO());
    }
}
