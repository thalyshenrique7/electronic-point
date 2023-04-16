package com.devthalys.electronicpoint.controllers;

import com.devthalys.electronicpoint.dtos.WorkedHoursDto;
import com.devthalys.electronicpoint.models.ElectronicPointModel;
import com.devthalys.electronicpoint.services.ElectronicPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/electronic-point")
public class ElectronicPointController {

    @Autowired
    private ElectronicPointService electronicPointService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ElectronicPointModel save(@RequestBody @Valid WorkedHoursDto employeeWorkedHours){
        return electronicPointService.save(employeeWorkedHours);
    }

}
