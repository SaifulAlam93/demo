package com.example.demo.controller;


import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
//        List<UserDto> dtoList;
//        try {
//            dtoList = userService.findAll();
//        }catch (Exception e){
////            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
//        }
//        return ResponseEntity.ok(dtoList);
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userName") final String userName) {
        return ResponseEntity.ok(userService.get(userName));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserDto userDTO)
            throws MethodArgumentNotValidException {
        if (userService.userNameExists(userDTO.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.emailExists(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        userDTO.setRoles(Collections.singleton("ROLE_USER"));
        try {
            userService.create(userDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("one of role01s not found"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "userName") final String userName,
            @RequestBody @Valid final UserDto userDTO) {
        userService.update(userName, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userName}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userName") final String userName) {
        userService.delete(userName);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        userDto.setRoles(Collections.singleton("ROLE_USER"));
        try {
            userService.create(userDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("one of role01s not found"));
        }
        return ResponseEntity.ok(new MessageResponse("User01 registered successfully!"));
    }


}
