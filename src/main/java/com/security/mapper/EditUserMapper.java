package com.security.mapper;

import com.security.model.User;
import com.security.model.dto.EditUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface EditUserMapper {

    User convert(EditUserDto editUserDto);

    void updateDto(@MappingTarget User user, EditUserDto editUserDto);
}
