package com.security.mapper;

import com.security.model.User;
import com.security.model.dto.CreateUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapper extends BaseMapper<User, CreateUserDto> {

    @Override
    User convert(CreateUserDto createUserDto);
}
