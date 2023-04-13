package com.devthalys.electronicpoint.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "employee")
public class EmployeeModel implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false, length = 70)
    private String address;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 10)
    @Temporal(TemporalType.DATE)
    private Date datefOfBirth;

    @Column(nullable = false, length = 10)
    private String registry;
}
