package com.devthalys.electronicpoint.controllers;

import com.devthalys.electronicpoint.dtos.EmployeeDto;
import com.devthalys.electronicpoint.exceptions.UserNotFoundException;
import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("{cpf}")
    public EmployeeModel findByCpf(@PathVariable String cpf){
        if(!service.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found in data base.");
        }
        return service.findByCpf(cpf);
    }

    @GetMapping
    public List<EmployeeModel> findAll(){
        return service.findAll();
    }

    @Transactional
    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeModel save(@RequestBody @Valid EmployeeDto employee) {
        if(service.existsByCpf(employee.getCpf())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already exists.");
        }

        var newEmployee = new EmployeeModel();
        BeanUtils.copyProperties(employee, newEmployee);
        newEmployee.setDatefOfBirth(LocalDate.now());
        return service.save(newEmployee);
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String cpf){
        service.delete(cpf);
    }

    @PutMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable String cpf, @RequestBody @Valid EmployeeModel employee) {
            if (service.existsByCpf(cpf)) {
                throw new UserNotFoundException("User not found in data base");
            }

        var newEmployee = new EmployeeModel();
        BeanUtils.copyProperties(employee, newEmployee);
        newEmployee.setId(employee.getId());
        service.save(newEmployee);
    }
}
