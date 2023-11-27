package com.sermaluc.msuser.infrastructure.adapters.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sermaluc.msuser.domain.service.UserService;
import com.sermaluc.msuser.infrastructure.adapters.output.eventPublisher.UserEventPublisherAdapter;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.UserPersistenceAdapter;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.repository.UserRepository;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
        return new UserPersistenceAdapter(userRepository, userPersistenceMapper);
    }

    @Bean
    public UserEventPublisherAdapter userEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new UserEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public UserService userService(UserPersistenceAdapter userPersistenceAdapter, UserEventPublisherAdapter userEventPublisherAdapter) {
        return new UserService(userPersistenceAdapter, userEventPublisherAdapter);
    }

}
