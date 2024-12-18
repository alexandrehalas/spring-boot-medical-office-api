package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;

import java.time.DayOfWeek;

public class OpeningHoursValidation {

    public void validate(AppointmentDto appointmentDto) {

        var appointmentDate = appointmentDto.appointmentDate();
        var isSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpenning = appointmentDate.getHour() < 7;
        var afterClinicOpenning = appointmentDate.getHour() > 18;

        if (isSunday || beforeClinicOpenning || afterClinicOpenning) {
            throw new BusinessException("Appointment outside of clinic opening hours");
        }
    }
}
