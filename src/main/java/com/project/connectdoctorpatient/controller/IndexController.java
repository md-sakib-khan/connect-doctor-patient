package com.project.connectdoctorpatient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sakib.khan
 * @since 2/27/22
 */
@Controller
@RequestMapping
public class IndexController {

    private static final String VIEW_PAGE = "index";

    @GetMapping
    public String show() {
        return VIEW_PAGE;
    }
}
