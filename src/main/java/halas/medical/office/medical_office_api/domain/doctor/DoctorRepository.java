package halas.medical.office.medical_office_api.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select d
                from Doctor d
            where
                d.active = true
                and d.doctorSpecialtyEnum = :doctorSpecialtyEnum
                and d.id not in (
                    select a.doctor.id
                    from Appointment a
                    where
                        a.appointmentDate = :appointmentDate
                        and a.cancelationReasonEnum is null
                )
                order by rand()
                limit 1
            """)
    Doctor findRandomDoctorAvailableOnTheDate(DoctorSpecialtyEnum doctorSpecialtyEnum, @NotNull @Future LocalDateTime appointmentDate);

    @Query("""
            select d.active
            from Doctor d
            where
                d.id = :id
            """)
    boolean findActiveById(Long id);
}
