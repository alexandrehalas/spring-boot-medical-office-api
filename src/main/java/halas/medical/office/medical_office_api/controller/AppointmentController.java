package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentResponseDetailDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentResponseDetailDto> schedule(@RequestBody @Valid AppointmentDto appointmentDto, UriComponentsBuilder uriComponentsBuilder) {

        System.out.println(appointmentDto);

        return ResponseEntity.ok(new AppointmentResponseDetailDto(null, null, null, null));
    }

}
