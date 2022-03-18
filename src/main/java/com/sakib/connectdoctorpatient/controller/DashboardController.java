package com.sakib.connectdoctorpatient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sakib.khan
 * @since 3/1/22
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final String VIEW_PAGE = "dashboard";

    @GetMapping
    public String showDashboard() {
        return VIEW_PAGE;
    }
}
