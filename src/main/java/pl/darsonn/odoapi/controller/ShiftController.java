package pl.darsonn.odoapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.darsonn.odoapi.model.dto.EndShiftRequest;
import pl.darsonn.odoapi.model.dto.ShiftResponse;
import pl.darsonn.odoapi.model.dto.StartShiftRequest;
import pl.darsonn.odoapi.service.ShiftService;

@RestController
@RequestMapping("api/shifts")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @PostMapping("/start")
    public ResponseEntity<ShiftResponse> startShift(@RequestBody StartShiftRequest request) {
        return ResponseEntity.status(201).body(shiftService.startShift(request));
    }

    @PostMapping("/{id}/end")
    private ResponseEntity<ShiftResponse> endShift(@PathVariable Long id, @RequestBody EndShiftRequest request) {
        return ResponseEntity.ok(shiftService.endShift(id, request));
    }
}
