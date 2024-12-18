package halas.medical.office.medical_office_api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentResponseDetailDto(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime appointmentDate) {

    public AppointmentResponseDetailDto(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getAppointmentDate());
    }
}
