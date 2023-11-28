package com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements Serializable {
	
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(
        message = "Email is not valid",
        regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}$",
        flags = Pattern.Flag.CASE_INSENSITIVE
    )
    private String email;

    @NotEmpty(message = "Password is required")
    @Pattern(
        message = "Password must be min 4 and max 12 length containing at least 1 uppercase, 1 lowercase, 1 special character and 1 digit",
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$"
    )
    private String password;

    @Valid
    private List<UserPhoneRequest> phones;
}
