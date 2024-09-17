package com.security.service;

import com.security.mapper.CreateUserMapper;
import com.security.mapper.EditUserMapper;
import com.security.mapper.ReadUserMapper;
import com.security.model.dto.CreateUserDto;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EditUserMapper editUserMapper;
    private final CreateUserMapper createUserMapper;
    private final ReadUserMapper readUserMapper;


    @Override
    public ReadUserDto create(CreateUserDto createUserDto) {
        return Optional.of(createUserDto)
                .map(createUserMapper::convert)
                .map(userRepository::saveAndFlush)
                .map(readUserMapper::convert)
                .orElseThrow();
    }

    @Override
    public Optional<ReadUserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(readUserMapper::convert);
    }

    @Override
    public List<ReadUserDto> findAll() {
        return userRepository.findAll().stream()
                .map(readUserMapper::convert)
                .toList();
    }

    @Override
    public Optional<ReadUserDto> update(Long id,
                                        EditUserDto editUserDto) {
        return userRepository.findById(id)
                .map(user -> {
                    editUserMapper.updateDto(user, editUserDto);
                    return user;
                })
                .map(readUserMapper::convert);

    }

    @Override
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
