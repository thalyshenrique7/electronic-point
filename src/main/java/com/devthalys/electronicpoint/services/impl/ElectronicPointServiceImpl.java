package com.devthalys.electronicpoint.services.impl;

import com.devthalys.electronicpoint.dtos.WorkedHoursDto;
import com.devthalys.electronicpoint.models.ElectronicPointModel;
import org.springframework.stereotype.Service;

@Service
public interface ElectronicPointServiceImpl {

    ElectronicPointModel save(WorkedHoursDto employeeWorkedHours);
}
