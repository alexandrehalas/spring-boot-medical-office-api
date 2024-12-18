package halas.medical.office.medical_office_api.domain.appointment.validations.schedule;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;

public interface ScheduleValidation {

    void validate(AppointmentDto appointmentDto);
}
