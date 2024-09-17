package com.security.mapper;

import com.security.model.User;
import com.security.model.dto.CreateUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface CreateUserMapper {

    User convert(CreateUserDto createUserDto);
}
