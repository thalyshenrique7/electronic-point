package com.devthalys.electronicpoint.services.impl;

import com.devthalys.electronicpoint.models.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeServiceImpl {

    Optional<EmployeeModel> findById(Long id);

    List<EmployeeModel> findAll();

    EmployeeModel save(EmployeeModel employee);

    void delete(Long id);
}
