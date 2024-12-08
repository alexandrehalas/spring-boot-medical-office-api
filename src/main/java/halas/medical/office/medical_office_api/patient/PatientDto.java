package halas.medical.office.medical_office_api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import halas.medical.office.medical_office_api.address.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientDto(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phoneNumber,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull
        @Valid
        @JsonProperty("address") AddressDto addressDto) {

}
