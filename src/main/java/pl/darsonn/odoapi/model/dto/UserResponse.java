package pl.darsonn.odoapi.model.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username
) {
}
