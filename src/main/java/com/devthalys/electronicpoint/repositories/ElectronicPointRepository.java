package com.devthalys.electronicpoint.repositories;

import com.devthalys.electronicpoint.models.ElectronicPointModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicPointRepository extends JpaRepository<ElectronicPointModel, Long> {


}
