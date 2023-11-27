package com.sermaluc.msuser.application.ports.input;

import com.sermaluc.msuser.domain.model.User;

public interface CreateUserUseCase {
    
    User createUser(User user);

}
