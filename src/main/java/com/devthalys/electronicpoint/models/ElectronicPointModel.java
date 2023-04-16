package com.devthalys.electronicpoint.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "electronic_point")
public class ElectronicPointModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime initialHour;

    @Column(nullable = false)
    private LocalDateTime finalHour;

    @Column
    private Duration duration;

    @OneToOne
    private EmployeeModel employee;
}
