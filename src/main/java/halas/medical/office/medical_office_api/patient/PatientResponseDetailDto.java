package halas.medical.office.medical_office_api.patient;

import halas.medical.office.medical_office_api.address.Address;

public record PatientResponseDetailDto(Long id, String name, String email, String phoneNumber, String cpf,
                                       Address address) {

    public PatientResponseDetailDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhoneNumber(), patient.getCpf(), patient.getAddress());
    }
}
