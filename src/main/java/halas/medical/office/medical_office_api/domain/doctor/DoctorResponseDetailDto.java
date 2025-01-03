package halas.medical.office.medical_office_api.domain.doctor;

import halas.medical.office.medical_office_api.domain.address.Address;

public record DoctorResponseDetailDto(Long id, String name, String email, String crm, DoctorSpecialtyEnum doctorSpecialtyEnum, Address address) {

    public DoctorResponseDetailDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getDoctorSpecialtyEnum(), doctor.getAddress());
    }

}
