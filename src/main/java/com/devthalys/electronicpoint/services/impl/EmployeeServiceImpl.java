package com.devthalys.electronicpoint.services.impl;

import com.devthalys.electronicpoint.models.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeServiceImpl {

    EmployeeModel findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    List<EmployeeModel> findAll();

    EmployeeModel save(EmployeeModel employee);

    void delete(String cpf);
}
