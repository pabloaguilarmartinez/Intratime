package com.mindden.mapper;

import com.mindden.entity.User;
import com.mindden.model.UserInfo;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsersMapperTest {
    private final EasyRandom generator = new EasyRandom();
    private final UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);

    @Test
    void whenMapEntityUserToDto_shouldReturnDto() {
        User userEntity = generator.nextObject(User.class);

        UserInfo userInfo = usersMapper.toDto(userEntity);

        assertEquals(userEntity.getId(), userInfo.getId());
        assertEquals(userEntity.getRole(), userInfo.getRole());
        assertEquals(userEntity.getEmail(), userInfo.getEmail());
        assertEquals(userEntity.getPassword(), userInfo.getPassword());
    }

    @Test
    void whenMapDtoUserToEntity_shouldReturnEntity() {
        UserInfo userInfo = generator.nextObject(UserInfo.class);

        User userEntity = usersMapper.toEntity(userInfo);

        assertEquals(userInfo.getEmail(), userEntity.getEmail());
        assertEquals(userInfo.getId(), userEntity.getId());
        assertEquals(userInfo.getRole(), userEntity.getRole());
        assertEquals(userInfo.getPassword(), userEntity.getPassword());
    }
}
