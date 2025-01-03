package halas.medical.office.medical_office_api.domain.patient;

import halas.medical.office.medical_office_api.domain.address.Address;
import jakarta.validation.constraints.NotNull;

public record PatientRequestUpdateDto(
        @NotNull Long id,
        String name,
        String phoneNumber,
        Address address) {
}
