package halas.medical.office.medical_office_api.domain.appointment.validations;

import halas.medical.office.medical_office_api.domain.appointment.AppointmentDto;
import halas.medical.office.medical_office_api.domain.patient.PatientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientActiveValidation {

    private final PatientRepository patientRepository;

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
