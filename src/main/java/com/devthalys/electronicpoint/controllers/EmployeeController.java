package com.devthalys.electronicpoint.controllers;

import com.devthalys.electronicpoint.dtos.EmployeeDto;
import com.devthalys.electronicpoint.dtos.UpdateCredentialsDto;
import com.devthalys.electronicpoint.exceptions.EmployeeNotFoundException;
import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("{cpf}")
    public EmployeeModel findByCpf(@PathVariable String cpf){
        if(!service.existsByCpf(cpf)){
            throw new EmployeeNotFoundException("Employee not found in data base.");
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
        return service.save(newEmployee);
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void deleteByCpf(@PathVariable String cpf){
        service.deleteByCpf(cpf);
    }

    @PutMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable String cpf, @RequestBody @Valid UpdateCredentialsDto updateCredentialsDto) {
            EmployeeModel employee = service.findByCpf(cpf);
            if (employee == null) {
                throw new EmployeeNotFoundException("Employee not found in data base");
            }

            var newEmployee = new EmployeeModel();
            BeanUtils.copyProperties(employee, newEmployee);

            newEmployee.setName(updateCredentialsDto.getName());
            newEmployee.setAddress(updateCredentialsDto.getAddress());
            newEmployee.setPayment(updateCredentialsDto.getPayment());
            newEmployee.setDepartment(updateCredentialsDto.getDepartment());

            service.save(newEmployee);
    }
}
