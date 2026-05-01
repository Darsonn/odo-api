package pl.darsonn.odoapi.model.dto;

public record VehicleResponse(
        Integer id,
        String registrationNumber,
        String makeAndModel
) {
}
