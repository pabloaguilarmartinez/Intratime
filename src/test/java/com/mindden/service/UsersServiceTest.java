package com.mindden.service;


import com.mindden.entity.User;
import com.mindden.mapper.CheckingsMapper;
import com.mindden.mapper.RequestsMapper;
import com.mindden.mapper.UsersMapper;
import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.repository.UsersRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    private final EasyRandom generator = new EasyRandom();
    @Mock
    private UsersMapper usersMapper = Mappers.getMapper(UsersMapper.class);
    @Mock
    private CheckingsMapper checkingsMapper = Mappers.getMapper(CheckingsMapper.class);
    @Mock
    private RequestsMapper requestsMapper = Mappers.getMapper(RequestsMapper.class);

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    void whenFindAll_thenReturnEmptyCollection() {
        List<User> emptyEntityList = generator.objects(User.class, 0).collect(Collectors.toList());
        Collection<UserInfo> emptyCollection = generator.objects(UserInfo.class, 0).collect(Collectors.toList());
        when(usersRepository.findAll()).thenReturn(emptyEntityList);
        when(usersMapper.toDtos(emptyEntityList)).thenReturn(emptyCollection);

        Collection<UserInfo> result = usersService.findAll();

        assertEquals(emptyCollection.size(), result.size());
        assertEquals(emptyCollection, result);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenFindAll_thenReturnCollectionOfUserInfo() {
        UserInfo userInfo = generator.nextObject(UserInfo.class);
        Collection<UserInfo> userInfoCollection = List.of(userInfo);
        when(usersMapper.toDtos(usersRepository.findAll())).thenReturn(userInfoCollection);

        Collection<UserInfo> result = usersService.findAll();

        assertEquals(userInfoCollection.size(), result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void
    whenFindCheckingsByUser_thenReturnEmptyCollection() {
        User user = generator.nextObject(User.class);
        Collection<CheckingBasicInfo> emptyCollection = generator.objects(CheckingBasicInfo.class, 0)
                .collect(Collectors.toList());
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(checkingsMapper.toBasicDtos(usersRepository.findById(any()).orElse(new User()).getCheckings()))
                .thenReturn(emptyCollection);

        Collection<CheckingBasicInfo> result = usersService.findCheckingsUserById(user.getId());

        assertEquals(emptyCollection, result);
        assertEquals(emptyCollection.size(), result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void
    whenFindCheckingsByUser_thenReturnCollectionOfCheckings() {
        User user = generator.nextObject(User.class);
        Collection<CheckingBasicInfo> emptyCollection = generator.objects(CheckingBasicInfo.class, 5)
                .collect(Collectors.toList());
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(checkingsMapper.toBasicDtos(usersRepository.findById(any()).orElse(new User()).getCheckings()))
                .thenReturn(emptyCollection);

        Collection<CheckingBasicInfo> result = usersService.findCheckingsUserById(user.getId());

        assertEquals(emptyCollection, result);
        assertEquals(emptyCollection.size(), result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void
    whenFindRequestsByUser_thenReturnEmptyCollection() {
        User user = generator.nextObject(User.class);
        Collection<RequestBasicInfo> emptyCollection = generator.objects(RequestBasicInfo.class, 0)
                .collect(Collectors.toList());
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(requestsMapper.toBasicDtos(usersRepository.findById(any()).orElse(new User()).getRequests()))
                .thenReturn(emptyCollection);

        Collection<RequestBasicInfo> result = usersService.findRequestsUserById(user.getId());

        assertEquals(emptyCollection, result);
        assertEquals(emptyCollection.size(), result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void
    whenFindReuquestsByUser_thenReturnCollectionOfRequests() {
        User user = generator.nextObject(User.class);
        Collection<RequestBasicInfo> emptyCollection = generator.objects(RequestBasicInfo.class, 5)
                .collect(Collectors.toList());
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(requestsMapper.toBasicDtos(usersRepository.findById(any()).orElse(new User()).getRequests()))
                .thenReturn(emptyCollection);

        Collection<RequestBasicInfo> result = usersService.findRequestsUserById(user.getId());

        assertEquals(emptyCollection, result);
        assertEquals(emptyCollection.size(), result.size());
        assertFalse(result.isEmpty());
    }
}
