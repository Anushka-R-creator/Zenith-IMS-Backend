package com.zenith.ims.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class AuthDtos {
    public record LoginRequest(
            @NotBlank
            String username,

            @NotBlank
            String password
    ) {}
    public record SignUpRequest(
            @NotBlank
            @Size(min = 3, max = 20)
            String username,

            @NotBlank
            @Size(max = 50)
            @Email
            String email,

            @NotBlank
            @Size(min = 6, max = 40)
            String password
    ) {}
    public record JwtAuthenticationResponse(
            String accessToken,
            String tokenType
    ) {}
}

