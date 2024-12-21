package halas.medical.office.medical_office_api.domain.doctor;

import halas.medical.office.medical_office_api.domain.address.Address;
import halas.medical.office.medical_office_api.domain.appointment.Appointment;
import halas.medical.office.medical_office_api.domain.appointment.CancelationReasonEnum;
import halas.medical.office.medical_office_api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Return null when registered doctor is not available")
    void findRandomDoctorAvailableOnTheDateScenario1() {

        // given or arrange
        var nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Test Doctor", "testdoctor@doctor.com", "41999999999", "123456", DoctorSpecialtyEnum.CARDIOLOGY, new Address("Nice Street", "Nice neighborhood", "11111111", "Nice city", "SP", "123", "House 2"));
        var patient = registerPatient("Test Patient", "testpatient@patient.com", "41888888888", "72121376003", new Address("Nice Street", "Nice neighborhood", "11111111", "Nice city", "SP", "125", null));
        registerAppointment(doctor, patient, nextMonday10am, null);

        // when or act
        var randomDoctorAvailableOnTheDate =
                doctorRepository.findRandomDoctorAvailableOnTheDate(DoctorSpecialtyEnum.CARDIOLOGY, nextMonday10am);

        // then or assert
        assertThat(randomDoctorAvailableOnTheDate).isNull();
    }

    @Test
    @DisplayName("Return doctor when doctor is available")
    void findRandomDoctorAvailableOnTheDateScenario2() {

        // given or arrange
        var nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Test Doctor", "testdoctor@doctor.com", "41999999999", "123456", DoctorSpecialtyEnum.CARDIOLOGY, new Address("Nice Street", "Nice neighborhood", "11111111", "Nice city", "SP", "123", "House 2"));

        // when or act
        var randomDoctorAvailableOnTheDate =
                doctorRepository.findRandomDoctorAvailableOnTheDate(DoctorSpecialtyEnum.CARDIOLOGY, nextMonday10am);

        // then or assert
        assertThat(randomDoctorAvailableOnTheDate).isEqualTo(doctor);
    }

    private Doctor registerDoctor(String name, String email, String phoneNumber, String crm, DoctorSpecialtyEnum doctorSpecialtyEnum, Address address) {
        return testEntityManager.persist(new Doctor(null, name, email, phoneNumber, crm, doctorSpecialtyEnum, address, true));
    }

    private Patient registerPatient(String name, String email, String phoneNumber, String cpf, Address address) {
        return testEntityManager.persist(new Patient(null, name, email, phoneNumber, cpf, address, true));
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date, CancelationReasonEnum cancelationReason) {
        testEntityManager.persist(new Appointment(null, doctor, patient, date, cancelationReason));
    }

}