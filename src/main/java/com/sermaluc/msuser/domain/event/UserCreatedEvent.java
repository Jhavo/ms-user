package com.sermaluc.msuser.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    private UUID id;

    private LocalDateTime date;

    public UserCreatedEvent(UUID id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

}
