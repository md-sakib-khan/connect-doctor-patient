package com.project.connectdoctorpatient.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OptimisticLockException;

/**
 * @author sakib.khan
 * @since 2/26/22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String VIEW_PAGE = "error/errorPage";

    private final MessageSourceAccessor msa;

    public GlobalExceptionHandler(MessageSourceAccessor msa) {
        this.msa = msa;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handle(MethodArgumentTypeMismatchException e) {
        return errorViewWithMessage(msa.getMessage("exception.input.type.mismatch"));
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) {
        return errorViewWithMessage(e.getMessage());
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ModelAndView handle(OptimisticLockException e) {
        return errorViewWithMessage(msa.getMessage("exception.parallel.update"));
    }

    private ModelAndView errorViewWithMessage(String message) {
        ModelAndView errorView = new ModelAndView(VIEW_PAGE);
        errorView.addObject("error", message);

        return errorView;
    }
}
