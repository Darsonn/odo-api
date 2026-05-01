package pl.darsonn.odoapi.model.dto;

import java.time.Instant;
import java.time.LocalDate;

public record ShiftResponse(
        Long id,
        String vehicleRegistrationNumber,
        String vehicleMakeAndModel,
        LocalDate shiftDate,
        Instant startTime,
        Integer startMileage,
        Instant endTime,
        Integer endMileage,
        Integer distanceDriven
) {
}
