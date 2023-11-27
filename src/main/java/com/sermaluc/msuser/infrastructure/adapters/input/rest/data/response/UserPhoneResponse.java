package com.sermaluc.msuser.infrastructure.adapters.input.rest.data.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPhoneResponse implements Serializable {

    private Long id;
    private String number;
    private String citycode;
    private String contrycode;

}
