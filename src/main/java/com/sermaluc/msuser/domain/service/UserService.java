package com.sermaluc.msuser.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.sermaluc.msuser.application.ports.input.CreateUserUseCase;
import com.sermaluc.msuser.application.ports.input.GetUserUseCase;
import com.sermaluc.msuser.application.ports.output.UserEventPublisher;
import com.sermaluc.msuser.application.ports.output.UserOutputPort;
import com.sermaluc.msuser.domain.event.UserCreatedEvent;
import com.sermaluc.msuser.domain.exception.UserNotFound;
import com.sermaluc.msuser.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements CreateUserUseCase, GetUserUseCase {
    
    private final UserOutputPort userOutputPort;
    private final UserEventPublisher userEventPublisher;

    @Override
    public User createUser(User user) {
		log.info("[UserService:createUser]: user: {}", user);
        user = userOutputPort.saveUser(user);
        userEventPublisher.publishUserCreatedEvent(new UserCreatedEvent(user.getId()));
        return user;
    }

    @Override
    public User getUserById(UUID id) {
		log.info("[UserService:getUserById]: id: {}", id);
        return userOutputPort.getUserById(id).orElseThrow(() -> new UserNotFound("User not found with id " + id));
    }

}
