package halas.medical.office.medical_office_api.domain.appointment;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.doctor.Doctor;
import halas.medical.office.medical_office_api.domain.doctor.DoctorRepository;
import halas.medical.office.medical_office_api.domain.patient.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public void schedule(AppointmentDto appointmentDto) {

        if (patientRepository.existsById(appointmentDto.idPatient())) {
            throw new BusinessException("Patient not found with ID: " + appointmentDto.idPatient());
        }

        if (appointmentDto.idDoctor() != null && doctorRepository.existsById(appointmentDto.idDoctor())) {
            throw new BusinessException("Doctor not found with ID: " + appointmentDto.idDoctor());
        }

        var patient = patientRepository.getReferenceById(appointmentDto.idPatient());
        var doctor = chooseDoctor(appointmentDto);
        var appointment = new Appointment(null, doctor, patient, appointmentDto.appointmentDate(), null);

        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentDto appointmentDto) {

        if (appointmentDto.idDoctor() != null) {
            return doctorRepository.getReferenceById(appointmentDto.idDoctor());
        }

        if (appointmentDto.doctorSpecialtyEnum() == null) {
            throw new BusinessException("Doctor's specialty is mandatory when the doctor is not chosen");
        }

        doctorRepository.findRandomDoctorAvailableOnTheDate(appointmentDto.doctorSpecialtyEnum(), appointmentDto.appointmentDate());

        return null;

    }

    public void cancel(@Valid CancelAppointmentDto cancelAppointmentDto) {

        if (!appointmentRepository.existsById(cancelAppointmentDto.idAppointment())) {
            throw new BusinessException("Appointment not found with ID: " + cancelAppointmentDto.idAppointment());
        }

        var appointment = appointmentRepository.getReferenceById(cancelAppointmentDto.idAppointment());

        appointment.cancel(cancelAppointmentDto.cancelationReasonEnum());
    }
}
