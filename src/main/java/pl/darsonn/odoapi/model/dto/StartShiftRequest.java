package pl.darsonn.odoapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record StartShiftRequest(
        @NotNull UUID userId,
        @NotNull Integer vehicleId,
        @NotNull @PositiveOrZero Integer startMileage
) {
}
