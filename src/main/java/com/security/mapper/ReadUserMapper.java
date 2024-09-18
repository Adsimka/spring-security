package com.security.mapper;

import com.security.model.User;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadUserMapper extends BaseMapper<ReadUserDto, User> {

    @Override
    ReadUserDto convert(User user);

    ReadUserDto convert(EditUserDto editUserDto);
}
