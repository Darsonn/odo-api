package pl.darsonn.odoapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username cannot be empty") String username
) {
}
