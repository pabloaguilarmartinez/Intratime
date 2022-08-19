package com.mindden.mapper;

import com.mindden.entity.Checking;
import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.CheckingInfo;
import com.mindden.model.CheckingNewInfo;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper
public interface CheckingsMapper {
	
	CheckingInfo toDto(Checking source);
	
	CheckingBasicInfo toBasicDto(Checking source);
	
	CheckingNewInfo toNewDto(Checking source);
	
	Checking toEntity(CheckingInfo source);

	Checking toNewEntity(CheckingNewInfo source);
	
	default Collection<CheckingInfo> toDtos(final Collection<Checking> sources) {
        return sources.stream().map(this::toDto).collect(toList());
    }

	default Collection<CheckingBasicInfo> toBasicDtos(final Collection<Checking> sources) {
        return sources.stream().map(this::toBasicDto).collect(toList());
    }
	
	default Collection<CheckingNewInfo> toNewDtos(final Collection<Checking> sources) {
        return sources.stream().map(this::toNewDto).collect(toList());
    }
}
