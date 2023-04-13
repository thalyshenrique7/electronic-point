package com.devthalys.electronicpoint.repositories;

import com.devthalys.electronicpoint.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    void deleteById(Long id);
}
