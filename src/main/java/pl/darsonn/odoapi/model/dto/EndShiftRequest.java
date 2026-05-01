package pl.darsonn.odoapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EndShiftRequest(
        @NotNull @PositiveOrZero Integer endMileage
) {
}
