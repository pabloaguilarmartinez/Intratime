package com.mindden.mapper;

import com.mindden.entity.Checking;
import com.mindden.model.CheckingInfo;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckingsMapperTest {
    private final EasyRandom generator = new EasyRandom();
    private final CheckingsMapper checkingsMapper = Mappers.getMapper(CheckingsMapper.class);

    @Test
    void whenMapEntityCheckingToDto_shouldReturnDto() {
        Checking checkingEntity = generator.nextObject(Checking.class);

        CheckingInfo checkingInfo = checkingsMapper.toDto(checkingEntity);

        assertEquals(checkingEntity.getDate(), checkingInfo.getDate());
        assertEquals(checkingEntity.getId(), checkingInfo.getId());
        assertEquals(checkingEntity.getType(), checkingInfo.getType());
        assertEquals(checkingEntity.getUser().getId(), checkingInfo.getUser().getId());
    }

    @Test
    void whenMapDtoCheckingToEntity_shouldReturnIEntity() {
        CheckingInfo checkingInfo = generator.nextObject(CheckingInfo.class);

        Checking checkingEntity = checkingsMapper.toEntity(checkingInfo);

        assertEquals(checkingInfo.getType(), checkingEntity.getType());
        assertEquals(checkingInfo.getUser().getId(), checkingEntity.getUser().getId());
        assertEquals(checkingInfo.getDate(), checkingEntity.getDate());
        assertEquals(checkingInfo.getId(), checkingEntity.getId());
    }
}
