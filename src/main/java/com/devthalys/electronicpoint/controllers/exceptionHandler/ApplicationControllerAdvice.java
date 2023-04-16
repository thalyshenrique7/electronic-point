package com.devthalys.electronicpoint.controllers.exceptionHandler;


import com.devthalys.electronicpoint.rest.ApiErrors;
import com.devthalys.electronicpoint.exceptions.EmployeeNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleUserNotFoundException(EmployeeNotFoundException e){
        String errorMessage = e.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }
}
