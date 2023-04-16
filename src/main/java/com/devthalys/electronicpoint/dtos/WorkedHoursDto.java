package com.devthalys.electronicpoint.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkedHoursDto implements Serializable {
    private static final long SerialVersionUID = 1L;

    @NotNull(message = "{required.field.id-employee}")
    private Long employee;

    @NotNull(message = "{required.field.initial-hour}")
    private LocalDateTime initialHour;

    @NotNull(message = "{required.field.final-hour}")
    private LocalDateTime finalHour;

    private Duration duration;
}
