package pl.darsonn.odoapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.darsonn.odoapi.model.dto.EndShiftRequest;
import pl.darsonn.odoapi.model.dto.ShiftResponse;
import pl.darsonn.odoapi.model.dto.StartShiftRequest;
import pl.darsonn.odoapi.model.entity.Shift;
import pl.darsonn.odoapi.model.entity.User;
import pl.darsonn.odoapi.model.entity.Vehicle;
import pl.darsonn.odoapi.repository.ShiftRepository;
import pl.darsonn.odoapi.repository.UserRepository;
import pl.darsonn.odoapi.repository.VehicleRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public ShiftResponse startShift(StartShiftRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Vehicle vehicle = vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Instant now = Instant.now();

        Shift shift = new Shift();
        shift.setUser(user);
        shift.setVehicle(vehicle);
        shift.setStartMileage(request.startMileage());
        shift.setStartTime(now);
        shift.setShiftDate(LocalDate.ofInstant(now, ZoneId.of("Europe/Warsaw")));

        return mapToResponse(shiftRepository.save(shift));
    }

    @Transactional
    public ShiftResponse endShift(Long shiftId, EndShiftRequest request) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found"));

        if(shift.getEndTime() != null) {
            throw new IllegalStateException("Shift is already completed.");
        }

        shift.setEndTime(Instant.now());
        shift.setEndMileage(request.endMileage());

        return mapToResponse(shiftRepository.save(shift));
    }

    private ShiftResponse mapToResponse(Shift shift) {
        Integer distance = null;
        if(shift.getEndMileage() != null) {
            distance = shift.getEndMileage() - shift.getStartMileage();
        }

        return new ShiftResponse(
                shift.getId(),
                shift.getVehicle().getRegistrationNumber(),
                shift.getVehicle().getMakeAndModel(),
                shift.getShiftDate(),
                shift.getStartTime(),
                shift.getStartMileage(),
                shift.getEndTime(),
                shift.getEndMileage(),
                distance
        );
    }
}
