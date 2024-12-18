package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.appointment.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorAppointmentInSameTimeValidation implements AppointmentValidation {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentDto appointmentDto) {

        var isDoctorWithAppointmentInSameDate = appointmentRepository.existsByDoctorIdAndAppointmentDate(appointmentDto.idDoctor(), appointmentDto.appointmentDate());

        if (isDoctorWithAppointmentInSameDate) {
            throw new BusinessException("Doctor already has an appointment scheduled at this same time");
        }
    }
}
