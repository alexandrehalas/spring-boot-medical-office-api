package halas.medical.office.medical_office_api.domain.appointment;

import halas.medical.office.medical_office_api.domain.BusinessException;
import halas.medical.office.medical_office_api.domain.appointment.validations.cancel.CancelAppointmentValidation;
import halas.medical.office.medical_office_api.domain.appointment.validations.schedule.ScheduleValidation;
import halas.medical.office.medical_office_api.domain.doctor.Doctor;
import halas.medical.office.medical_office_api.domain.doctor.DoctorRepository;
import halas.medical.office.medical_office_api.domain.patient.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final List<ScheduleValidation> scheduleValidations;
    private final List<CancelAppointmentValidation> cancelAppointmentValidations;

    public AppointmentResponseDetailDto schedule(AppointmentDto appointmentDto) {

        if (!patientRepository.existsById(appointmentDto.idPatient())) {
            throw new BusinessException("Patient not found with ID: " + appointmentDto.idPatient());
        }

        if (appointmentDto.idDoctor() != null && !doctorRepository.existsById(appointmentDto.idDoctor())) {
            throw new BusinessException("Doctor not found with ID: " + appointmentDto.idDoctor());
        }

        scheduleValidations.forEach(appointmentValidation -> appointmentValidation.validate(appointmentDto));

        var patient = patientRepository.getReferenceById(appointmentDto.idPatient());
        var doctor = chooseDoctor(appointmentDto);

        if (doctor == null) {
            throw new BusinessException("Doctor not available on this date");
        }

        var appointment = new Appointment(null, doctor, patient, appointmentDto.appointmentDate(), null);

        appointmentRepository.save(appointment);

        return new AppointmentResponseDetailDto(appointment);
    }

    private Doctor chooseDoctor(AppointmentDto appointmentDto) {

        if (appointmentDto.idDoctor() != null) {
            return doctorRepository.getReferenceById(appointmentDto.idDoctor());
        }

        if (appointmentDto.doctorSpecialtyEnum() == null) {
            throw new BusinessException("Doctor's specialty is mandatory when the doctor is not chosen");
        }

        return doctorRepository.findRandomDoctorAvailableOnTheDate(appointmentDto.doctorSpecialtyEnum(), appointmentDto.appointmentDate());
    }

    public void cancel(@Valid CancelAppointmentDto cancelAppointmentDto) {

        if (!appointmentRepository.existsById(cancelAppointmentDto.idAppointment())) {
            throw new BusinessException("Appointment not found with ID: " + cancelAppointmentDto.idAppointment());
        }

        cancelAppointmentValidations.forEach(cancelAppointmentValidation -> cancelAppointmentValidation.validate(cancelAppointmentDto));

        var appointment = appointmentRepository.getReferenceById(cancelAppointmentDto.idAppointment());

        appointment.cancel(cancelAppointmentDto.cancelationReasonEnum());
    }
}
