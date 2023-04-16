package com.devthalys.electronicpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")
public class EmployeeModel implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    @NotBlank(message = "{required.field.name-employee}")
    private String name;

    @Column(nullable = false, length = 70)
    @NotBlank(message = "{required.field.address-employee}")
    private String address;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "{required.field.cpf-employee}")
    private String cpf;

    @Column(nullable = false, length = 10)
    @NotNull(message = "{required.field.dateOfBirth-employee}")
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 10, unique = true)
    @NotBlank(message = "{required.field.registry-employee}")
    private String registry;

    @Column(nullable = false)
    @NotNull(message = "{required.field.payment-employee}")
    private BigDecimal payment;

    @Column(nullable = false)
    @NotBlank(message = "{required.field.department-employee}")
    private String department;

    @OneToOne
    @JsonIgnore
    private ElectronicPointModel electronicPoint;
}
