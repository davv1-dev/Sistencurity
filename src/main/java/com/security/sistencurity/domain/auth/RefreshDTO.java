package com.security.sistencurity.domain.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshDTO(
        @NotBlank
        String refreshtoken) {
}
