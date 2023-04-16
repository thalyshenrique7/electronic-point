package com.devthalys.electronicpoint.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }
}
