package com.mindden.mapper;

import com.mindden.entity.User;
import com.mindden.model.UserInfo;
import org.mapstruct.Mapper;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Mapper
public interface UsersMapper {
	
	UserInfo toDto(User source);
	
	User toEntity(UserInfo source);
	
	default Collection<UserInfo> toDtos(final Collection<User> sources) {
        return sources.stream().map(this::toDto).collect(toList());
    }

}