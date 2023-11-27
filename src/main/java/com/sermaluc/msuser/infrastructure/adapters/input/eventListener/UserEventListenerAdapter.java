package com.sermaluc.msuser.infrastructure.adapters.input.eventListener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sermaluc.msuser.domain.event.UserCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventListenerAdapter {

    @EventListener
    public void handle(UserCreatedEvent event){
        log.info("User created with id " + event.getId() + " at " + event.getDate());
    }

}
