package halas.medical.office.medical_office_api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import halas.medical.office.medical_office_api.domain.address.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDto(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phoneNumber,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        @JsonProperty("specialty") DoctorSpecialtyEnum doctorSpecialtyEnum,

        @NotNull
        @Valid
        @JsonProperty("address") AddressDto addressDto) {

}
