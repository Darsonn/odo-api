package pl.darsonn.odoapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record VehicleRequest(
        @NotBlank(message = "Registration number cannot be empty") String registrationNumber,
        @NotBlank(message = "Make and model cannot be empty") String makeAndModel
) {
}
