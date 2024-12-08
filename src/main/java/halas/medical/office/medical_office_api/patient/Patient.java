package halas.medical.office.medical_office_api.patient;

import halas.medical.office.medical_office_api.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String cpf;

    @Embedded
    private Address address;

    public Patient(PatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phoneNumber = patientDto.phoneNumber();
        this.cpf = patientDto.cpf();
        this.address = new Address(patientDto.addressDto());
    }

}
