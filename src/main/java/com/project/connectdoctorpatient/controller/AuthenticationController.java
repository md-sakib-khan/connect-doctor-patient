package com.project.connectdoctorpatient.controller;

import com.project.connectdoctorpatient.dto.LoginDTO;
import com.project.connectdoctorpatient.util.RedirectUtil;
import com.project.connectdoctorpatient.exception.NotFoundException;
import com.project.connectdoctorpatient.helper.AuthenticationHelper;
import com.project.connectdoctorpatient.service.AuthenticationService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.project.connectdoctorpatient.constant.URL.*;
import static com.project.connectdoctorpatient.controller.AuthenticationController.LOGIN_CMD;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author sakib.khan
 * @since 2/27/22
 */
@Controller
@SessionAttributes(LOGIN_CMD)
public class AuthenticationController {

    public static final String LOGIN_CMD = "loginDTO";
    public static final String LOGGED_USER_CMD = "loggedUser";

    public static final String VIEW_PAGE = "login";

    private final AuthenticationHelper authenticationHelper;

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationHelper authenticationHelper,
                                    AuthenticationService authenticationService) {

        this.authenticationHelper = authenticationHelper;
        this.authenticationService = authenticationService;
    }

    @InitBinder(LOGIN_CMD)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/login", method = GET)
    public String show(ModelMap model) {
        authenticationHelper.setupCommand(model);

        return VIEW_PAGE;
    }

    @RequestMapping(value = "/login", method = POST)
    public String process(@Valid @ModelAttribute(LOGIN_CMD) LoginDTO user,
                          Errors errors) throws NotFoundException {

        if (errors.hasErrors()) {
            return VIEW_PAGE;
        }

        authenticationService.authenticate(user);

        return RedirectUtil.redirect(DASHBOARD_URL);
    }


    @RequestMapping(value = "/logout", method = POST)
    private String logout(HttpSession session) {
        session.invalidate();

        return RedirectUtil.redirect(LOGIN_URL);
    }
}
