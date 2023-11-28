package com.sermaluc.msuser;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.domain.model.UserPhone;

public class MsUserUtilsTest {
    
    public static User createUser() {
        return User.builder()
            .id(UUID.fromString("cfc9cdb8-f522-45f6-aa9e-472574d2b8c4"))
            .name("Alexis Sanchez")
            .email("alexis@sanchez.cl")
            .password("H@nter1")
            .phones(List.of(createUserPhone()))
            .created(new Date())
            .modified(new Date())
            .lastLogin(new Date())
            .token("token")
            .isActive(true)
            .build();
    }
    
    public static UserPhone createUserPhone() {
        return UserPhone.builder()
            .id(1L)
            .userId(UUID.fromString("cfc9cdb8-f522-45f6-aa9e-472574d2b8c4"))
            .number("967328165")
            .cityCode("1")
            .countryCode("57")
            .created(new Date())
            .modified(new Date())
            .isActive(true)
            .build();
    }
}
