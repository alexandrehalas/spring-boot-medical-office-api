package halas.medical.office.medical_office_api.domain.appointment.validations.cancel;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentRepository;
import halas.medical.office.medical_office_api.domain.appointment.CancelAppointmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CancelTimeValidation implements CancelAppointmentValidation {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void validate(CancelAppointmentDto cancelAppointmentDto) {
        var appointment = appointmentRepository.getReferenceById(cancelAppointmentDto.idAppointment());
        var now = LocalDateTime.now();
        var differenceBetweenHours = Duration.between(now, appointment.getAppointmentDate()).toHours();

        if (differenceBetweenHours < 24) {
            throw new BusinessException("Appointment can only be canceled at least 24 hours in advance");
        }
    }
}
