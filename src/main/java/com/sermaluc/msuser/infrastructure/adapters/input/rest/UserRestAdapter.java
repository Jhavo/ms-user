package com.sermaluc.msuser.infrastructure.adapters.input.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sermaluc.msuser.application.ports.input.CreateUserUseCase;
import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request.CreateUserRequest;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.response.CreateUserResponse;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.mapper.UserRestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserRestAdapter {

    private final CreateUserUseCase createUserUseCase;
    private final UserRestMapper userRestMapper;

    @PostMapping(value = "/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
		log.info("[UserRestAdapter:createUser]: createUserRequest: {}", createUserRequest);
        User user = userRestMapper.toUser(createUserRequest);
        user = createUserUseCase.createUser(user);
        return new ResponseEntity<>(userRestMapper.toCreateUserResponse(user), HttpStatus.OK);
    }

}
