package com.sermaluc.msuser.infrastructure.adapters.input.rest.mapper;

import org.mapstruct.Mapper;

import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request.CreateUserRequest;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.response.CreateUserResponse;

@Mapper
public interface UserRestMapper {

    User toUser(CreateUserRequest createUserRequest);
    CreateUserResponse toCreateUserResponse(User user);

}
