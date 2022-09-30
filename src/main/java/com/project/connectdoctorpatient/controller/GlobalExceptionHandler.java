package com.project.connectdoctorpatient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OptimisticLockException;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String VIEW_PAGE = "error/errorPage";

    @Autowired
    private MessageSourceAccessor msa;

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) {
        ModelAndView errorView = new ModelAndView(VIEW_PAGE);
        errorView.addObject("error", e.getMessage());

        return errorView;
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ModelAndView handle(OptimisticLockException e) {
        ModelAndView errorView = new ModelAndView(VIEW_PAGE);
        errorView.addObject("error", msa.getMessage("exception.parallel.update"));

        return errorView;
    }
}
