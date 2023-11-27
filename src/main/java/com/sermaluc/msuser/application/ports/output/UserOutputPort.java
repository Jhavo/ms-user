package com.sermaluc.msuser.application.ports.output;

import java.util.Optional;
import java.util.UUID;

import com.sermaluc.msuser.domain.model.User;

public interface UserOutputPort {

    User saveUser(User user);
    Optional<User> getUserById(UUID id);

}
