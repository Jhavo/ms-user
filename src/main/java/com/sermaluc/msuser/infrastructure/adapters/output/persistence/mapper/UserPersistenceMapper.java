package com.sermaluc.msuser.infrastructure.adapters.output.persistence.mapper;

import org.mapstruct.Mapper;

import com.sermaluc.msuser.domain.model.User;
import com.sermaluc.msuser.infrastructure.adapters.output.persistence.entity.UserEntity;

@Mapper
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);
    User toUser(UserEntity userEntity);

}
