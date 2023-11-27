package com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements Serializable {

	@Valid
    @NotEmpty
	@NotNull
    private String name;

	@Valid
    @NotEmpty
	@NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

	@Valid
    @NotEmpty
	@NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
    message = "Password must be min 4 and max 12 length containing at least 1 uppercase, 1 lowercase, 1 special character and 1 digit")
    private String password;

	@Valid
    @NotEmpty
	@NotNull
    private List<UserPhoneRequest> phone;
}
