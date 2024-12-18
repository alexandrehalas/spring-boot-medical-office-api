package halas.medical.office.medical_office_api.domain.appointment.validations.cancel;

import halas.medical.office.medical_office_api.domain.appointment.CancelAppointmentDto;

public interface CancelAppointmentValidation {

    void validate(CancelAppointmentDto cancelAppointmentDto);
}
