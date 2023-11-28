package com.sermaluc.msuser.application.ports.output;

import com.sermaluc.msuser.domain.model.User;

public interface UserOutputPort {

    User saveUser(User user);

}
