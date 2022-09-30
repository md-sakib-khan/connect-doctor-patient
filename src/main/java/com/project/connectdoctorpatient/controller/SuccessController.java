package com.project.connectdoctorpatient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sakib.khan
 * @since 3/8/22
 */
@Controller
@RequestMapping("/success")
public class SuccessController {

    public static final String VIEW_PAGE = "alerts/success";

    @GetMapping
    public String show() {
        return VIEW_PAGE;
    }
}
