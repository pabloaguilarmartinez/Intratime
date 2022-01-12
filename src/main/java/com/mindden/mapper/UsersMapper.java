package com.mindden.mapper;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.mindden.entity.User;
import com.mindden.model.UserInfo;

@Mapper
public interface UsersMapper {
	
	UserInfo toDto(User source);
	
	User toEntity(UserInfo source);
	
	default List<UserInfo> toDtos(final Collection<User> sources) {
        return sources.stream().map(this::toDto).collect(toList());
    }

}