package com.mindden.mapper;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.mapstruct.Mapper;

import com.mindden.entity.Request;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.RequestInfo;

@Mapper
public interface RequestsMapper {
	
	RequestInfo toDto(Request source);
	
	RequestBasicInfo toBasicDto(Request source);
	
	Request toEntity(RequestInfo source);
	
	default Collection<RequestInfo> toDtos(final Collection<Request> sources) {
        return sources.stream().map(this::toDto).collect(toList());
    }
	
	default Collection<RequestBasicInfo> toBasicDtos(final Collection<Request> sources) {
        return sources.stream().map(this::toBasicDto).collect(toList());
    }

}
