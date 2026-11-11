package com.shelton.e_commerce.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelton.e_commerce.dto.request.UserRequest;
import com.shelton.e_commerce.dto.response.UserResponse;
import com.shelton.e_commerce.entity.IdempotencyKey;
import com.shelton.e_commerce.entity.User;
import com.shelton.e_commerce.entity.enums.AccountRole;
import com.shelton.e_commerce.exception.EmailAlreadyInUseException;
import com.shelton.e_commerce.exception.ResourceNotFoundException;
import com.shelton.e_commerce.mapper.UserMapper;
import com.shelton.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading account by username: {}", username);
        return userRepository.findByEmail(username).orElseThrow(() -> {
            log.warn("User not found for username: {}", username);
            return new UsernameNotFoundException("User not found. ");
        });
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        log.info("Register new User with id:{}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyInUseException("This login is already registered.");
        }

        User user = mapper.toEntity(request);
        user.setRole(AccountRole.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        log.info("User created successfully with id: {}", request.getEmail());
        return mapper.toResponse(saved);
    }

    public List<UserResponse> findAll(Pageable pageable) {
        log.info("Listing User records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<User> user = userRepository.findAll(pageable);
        List<UserResponse> userResponses = user.map(mapper::toResponse).getContent();
        log.info("Returning {} user(s)", userResponses.size());
        return userResponses;
    }

    public UserResponse findById(UUID id, Authentication authentication) {
        log.info("Loading User with id = {} ", id);

        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found: " + id));

        userAuthenticated(user, authentication);
        return mapper.toResponse(user);
    }


    @Transactional
    public UserResponse updateUser(UUID userId, UUID idempotencyKey, UserRequest userRequest, Authentication authentication) {
        log.info("Update user with id={}, idempotencyKey={}", userId, idempotencyKey);

        Optional<IdempotencyKey> cached = idempotencyKeyRepository.findById(idempotencyKey);
        if (cached.isPresent()) {
            log.info("Idempotency key {} já processada — devolvendo resposta em cache", idempotencyKey);
            return deserialize(cached.get().getResponseBody(), UserResponse.class);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        userAuthenticated(user, authentication);


        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User saved = userRepository.save(user);
        UserResponse response = mapper.toResponse(saved);

        persistIdempotencyRecord(idempotencyKey, "UPDATE_USER", userId, response);

        log.info("User updated successfully with id={}", userId);
        return response;
    }

    @Transactional
    public void deleteUser(UUID userId, UUID idempotencyKey) {
        log.info("Delete user with id={}, idempotencyKey={}", userId, idempotencyKey);

        if (idempotencyKeyRepository.existsById(idempotencyKey)) {
            log.info("Idempotency key {} já processada — delete é no-op", idempotencyKey);
            return;
        }

        if (!userRepository.existsById(userId)) {
            log.warn("User not found with id={}", userId);
            throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(userId);
        persistIdempotencyRecord(idempotencyKey, "DELETE_USER", userId, null);

        log.info("User deleted successfully with id={}", userId);
    }

    private void userAuthenticated(User user, Authentication authentication) {
        boolean userNotAuthenticated = user.getEmail().equals(authentication.getName());
        if (!userNotAuthenticated) {
            throw new AccessDeniedException("Access Denied!");
        }
    }

    private void persistIdempotencyRecord(UUID key, String operation, UUID resourceId, Object response) {
        IdempotencyKey record = IdempotencyKey.builder()
                .key(key)
                .operation(operation)
                .resourceId(resourceId)
                .responseBody(serialize(response))
                .createdAt(Instant.now())
                .build();
        try {
            idempotencyKeyRepository.save(record);
        } catch (DataIntegrityViolationException e) {
            log.warn("Idempotency key {} já estava a ser gravada em paralelo", key);
        }
    }

    private String serialize(Object value) {
        if (value == null) return null;
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProce e) {
            throw new IllegalStateException("Falha ao serializar resposta idempotente", e);
        }
    }

    private <T> T deserialize(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Falha ao desserializar resposta idempotente em cache", e);
        }
    }
}