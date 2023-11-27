package com.sermaluc.msuser.infrastructure.adapters.output.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.sermaluc.msuser.application.ports.output.UserOutputPort;
import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public User saveUser(User user) {
		log.info("[UserPersistenceAdapter:saveUser]: user: {}", user);
        UserEntity userEntity = userPersistenceMapper.toUserEntity(user);
        userEntity = userRepository.save(userEntity);
        return userPersistenceMapper.toUser(userEntity);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
		log.info("[UserPersistenceAdapter:getUserById]: id: {}", id);
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = userPersistenceMapper.toUser(userEntity.get());
        return Optional.of(user);
    }

}
