package com.company.rsvpedgeservice.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * The handler triggered by JSR validations
     * @param e MethodArgumentNotValidException
     * @param request request that did not supply correct input
     * @return VndErrors of all the reasons why the request failed
     */

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<VndErrors> notEnoughValues(MethodArgumentNotValidException e, WebRequest request) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<VndErrors.VndError> vndErrorList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            VndErrors.VndError vndError = new VndErrors.VndError(request.toString(), fieldError.getDefaultMessage());
            vndErrorList.add(vndError);
        }
        VndErrors errors = new VndErrors(vndErrorList);

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Thrown from the service layer if the number of guests attending is too high or too low
     * @param e IllegalArgumentException
     * @param request request that triggered the exception
     * @return message telling user if the number was too high or too low
     */

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<VndErrors> noEntityFoundForThatId(IllegalArgumentException e, WebRequest request) {
        VndErrors errors = new VndErrors(request.toString(), e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        return responseEntity;
    }

}
