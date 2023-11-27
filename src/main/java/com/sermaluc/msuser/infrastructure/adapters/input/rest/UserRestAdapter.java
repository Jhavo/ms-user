package com.sermaluc.msuser.infrastructure.adapters.input.rest;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sermaluc.msuser.application.ports.input.CreateUserUseCase;
import com.sermaluc.msuser.application.ports.input.GetUserUseCase;
import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.request.CreateUserRequest;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.response.CreateUserResponse;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.data.response.UserQueryResponse;
import com.sermaluc.msuser.infrastructure.adapters.input.rest.mapper.UserRestMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRestAdapter {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UserRestMapper userRestMapper;

    @PostMapping(value = "/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
		log.info("[UserRestAdapter:createUser]: createUserRequest: {}", createUserRequest);
        User user = userRestMapper.toUser(createUserRequest);
        user = createUserUseCase.createUser(user);
        return new ResponseEntity<>(userRestMapper.toCreateUserResponse(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserQueryResponse> getUser(@PathVariable UUID id){
		log.info("[UserRestAdapter:getUser]: id: {}", id);
        User user = getUserUseCase.getUserById(id);
        return new ResponseEntity<>(userRestMapper.toUserQueryResponse(user), HttpStatus.OK);
    }

}
