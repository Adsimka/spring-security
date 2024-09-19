package com.security.controller;

import com.security.model.dto.CreateUserDto;
import com.security.model.dto.EditUserDto;
import com.security.model.dto.ReadUserDto;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<ReadUserDto> create(CreateUserDto createUserDto) {
        var user = userService.create(createUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ReadUserDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    ResponseEntity<List<ReadUserDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<ReadUserDto> update(@PathVariable("id") Long id, EditUserDto editUserDto) {
        return new ResponseEntity<>(
                userService.update(id, editUserDto)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
