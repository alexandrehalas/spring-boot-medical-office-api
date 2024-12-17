package halas.medical.office.medical_office_api.domain.appointment;

public record CancelAppointmentDto(Long idAppointment, CancelationReasonEnum cancelationReasonEnum) {

}
