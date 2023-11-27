package com.sermaluc.msuser.domain.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request.UserPhoneRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<UserPhoneRequest> phone;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;

}
