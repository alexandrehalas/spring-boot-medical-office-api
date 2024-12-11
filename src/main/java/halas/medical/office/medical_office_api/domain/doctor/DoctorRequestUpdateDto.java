package halas.medical.office.medical_office_api.domain.doctor;

import halas.medical.office.medical_office_api.domain.address.Address;
import jakarta.validation.constraints.NotNull;

public record DoctorRequestUpdateDto(
        @NotNull Long id,
        String name,
        String phoneNumber,
        Address address) {
}
