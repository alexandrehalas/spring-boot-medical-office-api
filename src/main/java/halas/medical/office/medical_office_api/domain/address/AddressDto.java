package halas.medical.office.medical_office_api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(
        @NotBlank
        String streetAddress,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String number,

        @NotBlank
        String complement) {
}
