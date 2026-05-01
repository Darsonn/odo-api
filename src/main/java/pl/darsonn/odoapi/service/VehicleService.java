package pl.darsonn.odoapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.darsonn.odoapi.exception.ResourceAlreadyExistsException;
import pl.darsonn.odoapi.model.dto.VehicleRequest;
import pl.darsonn.odoapi.model.dto.VehicleResponse;
import pl.darsonn.odoapi.model.entity.Vehicle;
import pl.darsonn.odoapi.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Transactional
    public VehicleResponse createVehicle(VehicleRequest request) {
        if(vehicleRepository.existsByRegistrationNumber(request.registrationNumber())) {
            throw new ResourceAlreadyExistsException("Vehicle with registration '" + request.registrationNumber() + "' already exists.");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.registrationNumber());
        vehicle.setMakeAndModel(request.makeAndModel());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return mapToResponse(savedVehicle);
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getRegistrationNumber(),
                vehicle.getMakeAndModel()
        );
    }
}
