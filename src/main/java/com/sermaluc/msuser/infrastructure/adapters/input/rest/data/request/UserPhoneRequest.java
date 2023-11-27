package com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class UserPhoneRequest implements Serializable {

	@Valid
    @NotEmpty
	@NotNull
    private String number;

	@Valid
    @NotEmpty
	@NotNull
    private String citycode;

	@Valid
    @NotEmpty
	@NotNull
    private String contrycode;
    
}
