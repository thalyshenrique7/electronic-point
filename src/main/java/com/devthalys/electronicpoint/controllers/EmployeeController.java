package com.devthalys.electronicpoint.controllers;

import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("{id}")
    public Optional<EmployeeModel> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<EmployeeModel> findAll(){
        return service.findAll();
    }

    @Transactional
    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeModel save(@RequestBody EmployeeModel employee){
        employee.setDatefOfBirth(Date.from(Instant.now()));
        return service.save(employee);
    }

    @Transactional
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
