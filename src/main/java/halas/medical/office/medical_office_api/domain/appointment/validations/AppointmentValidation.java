package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;

public interface AppointmentValidation {

    void validate(AppointmentDto appointmentDto);
}
