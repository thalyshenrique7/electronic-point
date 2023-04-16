package com.devthalys.electronicpoint.services;

import com.devthalys.electronicpoint.dtos.WorkedHoursDto;
import com.devthalys.electronicpoint.exceptions.EmployeeNotFoundException;
import com.devthalys.electronicpoint.models.ElectronicPointModel;
import com.devthalys.electronicpoint.models.EmployeeModel;
import com.devthalys.electronicpoint.repositories.EmployeeRepository;
import com.devthalys.electronicpoint.services.impl.ElectronicPointServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class ElectronicPointService implements ElectronicPointServiceImpl {

    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public ElectronicPointModel save(WorkedHoursDto employeeWorkedHours) {
        Long idEmployee = employeeWorkedHours.getEmployee();
        EmployeeModel employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found in data base"));

        ElectronicPointModel electronicPoint = new ElectronicPointModel();
        electronicPoint.setInitialHour(employeeWorkedHours.getInitialHour());
        electronicPoint.setFinalHour(employeeWorkedHours.getFinalHour());

        Duration duration = Duration.between(employeeWorkedHours.getInitialHour(), employeeWorkedHours.getFinalHour());
        electronicPoint.setId(idEmployee);
        electronicPoint.setDuration(duration);
        electronicPoint.setEmployee(employee);

        return electronicPoint;
    }
}
