package com.shelton.e_commerce.controller;
import com.shelton.e_commerce.dto.request.UserRequest;
import com.shelton.e_commerce.dto.response.UserResponse;
import com.shelton.e_commerce.entity.User;
import com.shelton.e_commerce.service.TokenService;
import com.shelton.e_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login( @RequestBody  UserRequest request) {
        log.info("Receiving Email request for user: {}", request.getEmail());
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var token = tokenService.generatedToken( (User) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token));
    }

    @PostMapping("/create/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody  UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id, Authentication authentication) {
        return ResponseEntity.ok(service.findById(id, authentication));
    }
}

