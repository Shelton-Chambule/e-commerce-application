package com.shelton.e_commerce.service;
import com.shelton.e_commerce.entity.User;
import com.shelton.e_commerce.exception.ResourceNotFoundException;
import com.shelton.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("User not authenticated");
        }

        String email = extractEmail(authentication);
        return userRepository.findByEmail(email).orElseThrow(() -> { return new ResourceNotFoundException(email);});
    }

    private String extractEmail(Authentication authentication) {

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            return user.getEmail();
        }

        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }

        if (principal instanceof String email) {
            return email;
        }

        String name = authentication.getName();

        if (name == null || name.isBlank() || "anonymousUser".equals(name)) {
            throw new AccessDeniedException("User not authenticated");
        }
        return name;
    }
}
