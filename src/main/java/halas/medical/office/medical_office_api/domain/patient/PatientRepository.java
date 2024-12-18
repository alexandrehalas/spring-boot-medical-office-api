package halas.medical.office.medical_office_api.domain.patient;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select p.active
            from Patient p
            where
                p.id :id
            """)
    boolean findActiveById(@NotNull Long id);
}
