package com.devthalys.electronicpoint.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private static final long SerialVersionUID = 1L;

    @NotBlank(message = "{required.field.name-employee}")
    @Size(max = 70)
    private String name;

    @NotBlank(message = "{required.field.address-employee}")
    @Size(max = 70)
    private String address;

    @NotBlank(message = "{required.field.cpf-employee}")
    @CPF(message = "{required.field.cpf-valid}")
    private String cpf;

    @NotNull(message = "{required.field.dateOfBirth-employee}")
    private LocalDate dateOfBirth;

    @NotBlank(message = "{required.field.registry-employee}")
    private String registry;

    @NotNull(message = "{required.field.payment-employee}")
    private BigDecimal payment;

    @NotBlank(message = "{required.field.department-employee}")
    private String department;

}
