package com.sakib.connectdoctorpatient.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author sakib.khan
 * @since 2/27/22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @Email(message = "{error.email}")
    @NotNull(message = "{error.null}")
    private String email;

    @NotNull(message = "{error.null}")
    @Size(min = 4, max = 100, message = "{error.size}")
    private String password;
}
