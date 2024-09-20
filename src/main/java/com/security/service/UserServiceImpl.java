package com.security.service;

import com.security.mapper.CreateUserMapper;
import com.security.mapper.EditUserMapper;
import com.security.mapper.ReadUserMapper;
import com.security.model.dto.CreateUserDto;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;
import com.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final EditUserMapper editUserMapper;
    private final CreateUserMapper createUserMapper;
    private final ReadUserMapper readUserMapper;

    @Override
    @Transactional
    public ReadUserDto create(CreateUserDto createUserDto) {
        CreateUserDto dto = new CreateUserDto("Arseniy", "Minnegulov", LocalDate.of(2002, 3, 24), "sahalysyk02@mail.ru", "1234");

        return Optional.of(dto)
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
    @Transactional
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
    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user by username: " + username));
    }
}
