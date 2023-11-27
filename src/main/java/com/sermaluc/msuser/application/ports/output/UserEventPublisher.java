package com.sermaluc.msuser.application.ports.output;

import com.sermaluc.msuser.domain.event.UserCreatedEvent;

public interface UserEventPublisher {

    void publishUserCreatedEvent(UserCreatedEvent event);

}
