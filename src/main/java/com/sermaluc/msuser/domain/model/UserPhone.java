package com.sermaluc.msuser.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
public class UserPhone implements Serializable {

    private Long id;
    private UUID userId;
    private String number;
    private String cityCode;
    private String countryCode;
    private Date created;
    private Date modified;
    private Boolean isActive;

}
