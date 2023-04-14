package com.devthalys.electronicpoint.controllers.exceptionHandler;


import com.devthalys.electronicpoint.ApiErrors;
import com.devthalys.electronicpoint.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleUserNotFoundException(UserNotFoundException e){
        String errorMessage = e.getMessage();
        return new ApiErrors(errorMessage);
    }
}
