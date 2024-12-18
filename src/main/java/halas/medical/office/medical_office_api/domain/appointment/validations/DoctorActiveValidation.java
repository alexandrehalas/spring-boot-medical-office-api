package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.doctor.DoctorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DoctorActiveValidation {

    private final DoctorRepository doctorRepository;

    public void validate(AppointmentDto appointmentDto) {

        if (appointmentDto.idDoctor() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(appointmentDto.idDoctor());

        if (!doctorIsActive) {
            throw new RuntimeException("Appointments cannot be scheduled with an inactive doctor");
        }
    }
}
