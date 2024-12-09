package halas.medical.office.medical_office_api.doctor;

import halas.medical.office.medical_office_api.address.Address;
import jakarta.validation.constraints.NotNull;

public record DoctorRequestUpdateDto(
        @NotNull Long id,
        String name,
        String phoneNumber,
        Address address) {
}
