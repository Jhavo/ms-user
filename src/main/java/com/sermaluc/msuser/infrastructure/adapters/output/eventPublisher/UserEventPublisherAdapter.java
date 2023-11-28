package com.sermaluc.msuser.infrastructure.adapters.output.eventPublisher;

import org.springframework.context.ApplicationEventPublisher;

import com.sermaluc.msuser.application.ports.output.UserEventPublisher;
import com.sermaluc.msuser.domain.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserEventPublisherAdapter implements UserEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishUserCreatedEvent(UserCreatedEvent event) {
		log.info("[UserEventPublisherAdapter:publishUserCreatedEvent]: event: {}", event);
        applicationEventPublisher.publishEvent(event);
    }

}
