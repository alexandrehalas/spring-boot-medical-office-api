package halas.medical.office.medical_office_api.domain.appointment;

import halas.medical.office.medical_office_api.domain.doctor.DoctorSpecialtyEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime appointmentDate,

        DoctorSpecialtyEnum doctorSpecialtyEnum) {
}
