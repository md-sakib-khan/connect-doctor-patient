package com.sakib.connectdoctorpatient.helper;

import com.sakib.connectdoctorpatient.dto.LoginDTO;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import static com.sakib.connectdoctorpatient.controller.AuthenticationController.LOGIN_CMD;

/**
 * @author sakib.khan
 * @since 3/6/22
 */
@Component
public class AuthenticationHelper {

    public void setupCommand(ModelMap model) {
        model.addAttribute(LOGIN_CMD, new LoginDTO());
    }
}
