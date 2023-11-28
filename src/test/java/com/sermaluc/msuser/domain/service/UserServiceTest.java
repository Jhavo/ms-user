package com.sermaluc.msuser.domain.service;

import static com.sermaluc.msuser.MsUserUtilsTest.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sermaluc.msuser.application.ports.output.UserEventPublisher;
import com.sermaluc.msuser.application.ports.output.UserOutputPort;
import com.sermaluc.msuser.domain.exception.EmailAlreadyExist;
import com.sermaluc.msuser.domain.model.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @InjectMocks
    private UserService userService;
    @Mock
    private UserOutputPort userOutputPort;
    @Mock
    private UserEventPublisher userEventPublisher;

    @BeforeEach
    void setup() {
        userService = new UserService(
            userOutputPort,
            userEventPublisher);
    }

    @Test
    void PersonalLoanServiceImpl_getPersonalLoan_test_01() {
        // when
        when(userOutputPort.saveUser(any(User.class))).thenReturn(createUser());
        User result = userService.createUser(createUser());
        // then
        assertThat(result).isNotNull();
    }

}
