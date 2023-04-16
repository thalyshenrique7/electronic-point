package com.devthalys.electronicpoint.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto {

    @NotEmpty(message = "{required.field.username-user}")
    private String username;

    @NotEmpty(message = "{required.field.password-user}")
    private String password;
}
