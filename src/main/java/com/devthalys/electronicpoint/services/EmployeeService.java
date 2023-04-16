package com.devthalys.electronicpoint.services;

import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.repositories.EmployeeRepository;
import com.devthalys.electronicpoint.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeModel findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public List<EmployeeModel> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public EmployeeModel save(EmployeeModel employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteByCpf(String cpf) {
        repository.deleteByCpf(cpf);
    }

}
