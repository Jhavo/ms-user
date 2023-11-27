package com.sermaluc.msuser.application.ports.input;

import java.util.UUID;

import com.sermaluc.msuser.domain.model.User;

public interface GetUserUseCase {

    User getUserById(UUID id);

}
