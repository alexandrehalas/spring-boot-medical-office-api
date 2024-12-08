package halas.medical.office.medical_office_api.doctor;

import halas.medical.office.medical_office_api.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String crm;

    @Column(name = "doctor_specialty")
    @Enumerated(EnumType.STRING)
    private DoctorSpecialtyEnum doctorSpecialtyEnum;

    @Embedded
    private Address address;

    public Doctor(DoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.phoneNumber = doctorDto.phoneNumber();
        this.crm = doctorDto.crm();
        this.doctorSpecialtyEnum = doctorDto.doctorSpecialtyEnum();
        this.address = new Address(doctorDto.addressDto());
    }
}
