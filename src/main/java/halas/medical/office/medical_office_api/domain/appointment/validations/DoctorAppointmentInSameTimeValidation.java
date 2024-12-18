package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DoctorAppointmentInSameTimeValidation {

    private final AppointmentRepository appointmentRepository;

    public void validate(AppointmentDto appointmentDto) {

        var isDoctorWithAppointmentInSameDate = appointmentRepository.existsByDoctorIdAndAppointmentDate(appointmentDto.idDoctor(), appointmentDto.appointmentDate());

        if (isDoctorWithAppointmentInSameDate) {
            throw new BusinessException("Doctor already has an appointment scheduled at this same time");
        }
    }
}
