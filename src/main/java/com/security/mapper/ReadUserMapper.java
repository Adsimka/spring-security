package com.security.mapper;

import com.security.model.User;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReadUserMapper {

    ReadUserDto convert(User user);

    ReadUserDto convert(EditUserDto editUserDto);
}
