package com.devthalys.electronicpoint.services;

import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.repositories.EmployeeRepository;
import com.devthalys.electronicpoint.services.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService implements EmployeeServiceImpl {

    private final EmployeeRepository repository;
    @Override
    public Optional<EmployeeModel> findById(Long id) {
        return repository.findById(id);
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

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
