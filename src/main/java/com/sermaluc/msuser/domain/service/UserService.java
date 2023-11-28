package com.sermaluc.msuser.domain.service;

import com.sermaluc.msuser.application.ports.input.CreateUserUseCase;
import com.sermaluc.msuser.application.ports.output.UserEventPublisher;
import com.sermaluc.msuser.application.ports.output.UserOutputPort;
import com.sermaluc.msuser.domain.event.UserCreatedEvent;
import com.sermaluc.msuser.domain.exception.EmailAlreadyExist;
import com.sermaluc.msuser.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UserService implements CreateUserUseCase {
    
    private final UserOutputPort userOutputPort;
    private final UserEventPublisher userEventPublisher;

    @Override
    public User createUser(User user) {
        try {
            log.info("[UserService:createUser]: user: {}", user);
            user = userOutputPort.saveUser(user);
            userEventPublisher.publishUserCreatedEvent(new UserCreatedEvent(user.getId()));
            return user;
        } catch (Exception e) {
            throw new EmailAlreadyExist("Email already exist");
        }
    }

}
