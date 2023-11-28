package com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

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
public class UserPhoneRequest implements Serializable {

	@NotEmpty(message = "Number is required")
    private String number;

	@NotEmpty(message = "City code is required")
    private String cityCode;

	@NotEmpty(message = "Country code is required")
    private String countryCode;
    
}
