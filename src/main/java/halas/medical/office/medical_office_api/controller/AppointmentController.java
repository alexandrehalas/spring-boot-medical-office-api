package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentResponseDetailDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentService;
import halas.medical.office.medical_office_api.domain.appointment.CancelAppointmentDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentResponseDetailDto> schedule(@RequestBody @Valid AppointmentDto appointmentDto, UriComponentsBuilder uriComponentsBuilder) {

        appointmentService.schedule(appointmentDto);

        return ResponseEntity.ok(new AppointmentResponseDetailDto(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody @Valid CancelAppointmentDto cancelAppointmentDto) {

        appointmentService.cancel(cancelAppointmentDto);

        return ResponseEntity.noContent().build();
    }

}
