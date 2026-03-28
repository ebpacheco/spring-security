package dev.eduardo.security.config;

import lombok.Builder;

@Builder
public record JWTUSerData(Long userId, String email) {
}
