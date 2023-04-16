package com.devthalys.electronicpoint.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserModel implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    @NotEmpty(message = "{required.field.username-user}")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "{required.field.password-user}")
    private String password;

    @Column
    private boolean admin;
}
