package com.sermaluc.msuser.infrastructure.adapters.output.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.sermaluc.msuser.application.ports.output.UserOutputPort;
import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.repository.UserRepository;
import com.sermaluc.msuser.infrastructure.adapters.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User saveUser(User user) {
		log.info("[UserPersistenceAdapter:saveUser]: user: {}", user);
        UserEntity userEntity = userPersistenceMapper.toUserEntity(user);
        userEntity.setToken(jwtUtil.createToken(user));
        userEntity = userRepository.save(userEntity);
        return userPersistenceMapper.toUser(userEntity);
    }

}
