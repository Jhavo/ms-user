package com.sermaluc.msuser.domain.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent implements Serializable {

    private UUID id;

    private LocalDateTime date;

    public UserCreatedEvent(UUID id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

}
