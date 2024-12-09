package halas.medical.office.medical_office_api.controller;

import halas.medical.office.medical_office_api.address.Address;
import jakarta.validation.constraints.NotNull;

public record PatientRequestUpdateDto(
        @NotNull Long id,
        String name,
        String phoneNumber,
        Address address) {
}
