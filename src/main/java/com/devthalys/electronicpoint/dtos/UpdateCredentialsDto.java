package com.devthalys.electronicpoint.dtos;

import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class UpdateCredentialsDto {

    @Size(max = 70)
    private String name;

    @Size(max = 70)
    private String address;

    private BigDecimal payment;

    private String department;

}
