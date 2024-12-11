package halas.medical.office.medical_office_api.domain.patient;

import halas.medical.office.medical_office_api.domain.address.Address;
import halas.medical.office.medical_office_api.controller.PatientRequestUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    private boolean active;

    public Patient(PatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phoneNumber = patientDto.phoneNumber();
        this.cpf = patientDto.cpf();
        this.address = new Address(patientDto.addressDto());
        this.active = true;
    }

    public void updateData(@Valid PatientRequestUpdateDto patientRequestUpdateDto) {
        if (patientRequestUpdateDto.name() != null) {
            this.name = patientRequestUpdateDto.name();
        }
        if (patientRequestUpdateDto.phoneNumber() != null) {
            this.phoneNumber = patientRequestUpdateDto.phoneNumber();
        }
        if (patientRequestUpdateDto.address() != null) {
            this.address.updateData(patientRequestUpdateDto.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
