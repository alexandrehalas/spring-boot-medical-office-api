package halas.medical.office.medical_office_api.domain.appointment.validations.schedule;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientActiveValidation implements ScheduleValidation {

    private final PatientRepository patientRepository;

    @Override
    public void validate(AppointmentDto appointmentDto) {

        if (appointmentDto.idPatient() == null) {
            return;
        }

        var patientIsActive = patientRepository.findActiveById(appointmentDto.idPatient());

        if (!patientIsActive) {
            throw new RuntimeException("Appointments cannot be scheduled with an inactive patient");
        }
    }
}
