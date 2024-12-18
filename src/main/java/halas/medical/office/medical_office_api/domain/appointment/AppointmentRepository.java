package halas.medical.office.medical_office_api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndAppointmentDate(Long idDoctor, LocalDateTime appointmentDate);
}
