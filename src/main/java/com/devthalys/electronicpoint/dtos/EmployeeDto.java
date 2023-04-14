package com.devthalys.electronicpoint.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "The name must be informed!")
    @Size(max = 70)
    private String name;

    @NotBlank(message = "The address must be informed!")
    @Size(max = 70)
    private String address;

    @NotBlank(message = "CPF must be informed!")
    @CPF(message = "CPF must be valid!")
    private String cpf;

    @NotNull(message = "Date of birth must be informed!")
    private LocalDate dateOfBirth;

    @NotBlank(message = "The registry must be informed!")
    private String registry;

}
