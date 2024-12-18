package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;

import java.time.Duration;
import java.time.LocalDateTime;

public class HoursInAdvanceValidation {

    public void validate(AppointmentDto appointmentDto) {

        var appointmentDate = appointmentDto.appointmentDate();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new BusinessException("The appointment must be scheduled 30 minutes in advance");
        }
    }
}
